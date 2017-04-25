package com.solteam.dictionary.web;

import com.solteam.dictionary.domain.Dictionary;
import com.solteam.dictionary.service.DictionaryService;
import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Burak Baldirlioglu
 * @since 4/16/2017 10:39 AM
 */
@SpringUI(path = "ui")
@Theme("valo")
public class DictionaryUI extends UI {

    private final DictionaryService dictionaryService;

    private GridLayout gridLayout;
    private Grid<Dictionary> grid;
    private TextField textField;

    private List<Dictionary> words;

    @Autowired
    DictionaryUI(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        gridLayout = new GridLayout(2, 1);
        gridLayout.setSpacing(true);
        gridLayout.setMargin(new MarginInfo(true, true, false, true));

        grid = new Grid<>(Dictionary.class);
        grid.setColumnOrder("id", "word", "meaning");
        grid.setVisible(false);

        textField = new TextField("Enter the word");
        textField.addShortcutListener(new ShortcutListener("Add Shortcut", ShortcutAction.KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                addWordClicked(null);
            }
        });
        Button addWordButton = new Button("Add", this::addWordClicked);
        Button getAllWordButton = new Button("See/Hide All Words", this::getAllWordClicked);
        Button getSingleWordButton = new Button("Try a Word", this::getSingleWordClicked);
        getSingleWordButton.setVisible(false);

        FormLayout formLayout = new FormLayout();
        formLayout.addComponent(textField);
        formLayout.addComponent(addWordButton);
        formLayout.addComponent(getAllWordButton);
        formLayout.addComponent(getSingleWordButton);
        formLayout.addComponent(new Label("Powered by Yandex.Translate"));

        gridLayout.addComponent(formLayout);
        gridLayout.addComponent(grid);

        setContent(gridLayout);
    }

    private void setItems() {
        words = dictionaryService.getAllWords();
        grid.setItems(words);
    }

    private void addWordClicked(Button.ClickEvent clickEvent) {
        String value = textField.getValue();
        if (StringUtils.isEmpty(value)) {
            return;
        }
        if (value.contains(" ")) {
            return;
        }
        if (value.length() > 100) {
            return;
        }
        try {
            dictionaryService.addNewWord(value);
            showInfoMessage(value);
        } catch (Exception e) {
            String description = e.getMessage() != null ? e.getMessage() : e.toString();
            showErrorMessage(description);
            return;
        }
        resetToDefault();
    }

    private void showInfoMessage(String message) {
        Notification notification = new Notification("New word added:", message, Notification.Type.HUMANIZED_MESSAGE);
        notification.show(Page.getCurrent());
    }

    private void showErrorMessage(String description) {
        Notification notification = new Notification("Error occured:", description, Notification.Type.ERROR_MESSAGE);
        notification.show(Page.getCurrent());
    }

    private void getAllWordClicked(Button.ClickEvent clickEvent) {
        if (grid.isVisible()) {
            grid.setVisible(false);
        } else {
            setItems();
            grid.setVisible(true);
        }
    }

    private void getSingleWordClicked(Button.ClickEvent clickEvent) {
        Dictionary dictionary = words.get(LocalDateTime.now().getNano() % words.size());
        String word = dictionary.getWord();
    }

    private void resetToDefault() {
        textField.clear();
        if (grid.isVisible()) {
            setItems();
        }
    }
    /*@WebServlet(urlPatterns = "*//*", name = "DictionaryUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = DictionaryUI.class, productionMode = false)
    public static class DictionaryUIServlet extends VaadinServlet {
    }*/
}
