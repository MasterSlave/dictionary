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
    private Button addWordButton;
    private Button getAllWordButton;
    private Button getSingleWordButton;

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
        addWordButton = new Button("Add", this::addWordClicked);
        getAllWordButton = new Button("See/Hide All Words", this::getAllWordClicked);
        getSingleWordButton = new Button("Try a Word", this::getSingleWordClicked);

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
        grid.setItems(dictionaryService.getAllWords());
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
        } catch (Exception e) {
            String description = e.getMessage() != null ? e.getMessage() : e.toString();
            Notification notification = new Notification("Error occured:", description);
            notification.show(Page.getCurrent());
            return;
        }
        resetToDefault();
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
