# Cheatsheet JavaFX - Syntaxe générique des Menus


## MenuBar et Menu

### Création de base
```java
// Barre de menu principale
MenuBar menuBar = new MenuBar();

// Menus principaux
Menu menu1 = new Menu("Nom du menu");
Menu menu2 = new Menu("Autre menu");

// Ajout des menus à la barre
menuBar.getMenus().add(menu1);
menuBar.getMenus().addAll(menu1, menu2, menu3);

// Placement dans le conteneur
root.setTop(menuBar);  // BorderPane
// ou root.getChildren().add(menuBar);  // VBox/HBox
```

### Options des Menu
```java
Menu menu = new Menu("Nom");
menu.setText("Nouveau nom");           // Changer le texte
menu.setDisable(true/false);          // Activer/désactiver
menu.setVisible(true/false);          // Visible/invisible
menu.setGraphic(new ImageView(image)); // Icône
```

## MenuItem (éléments de menu simples)

### Création et options
```java
// Création de base
MenuItem item = new MenuItem("Nom de l'item");

// Options disponibles
item.setText("Nouveau nom");
item.setDisable(true/false);
item.setVisible(true/false);
item.setGraphic(new ImageView(image));          // Icône
item.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
item.setMnemonicParsing(true/false);            // Soulignement automatique

// Ajout au menu
menu.getItems().add(item);
menu.getItems().addAll(item1, item2, item3);
```

## RadioMenuItem (choix exclusif)

### Syntaxe 
```java
// Création
RadioMenuItem radioItem = new RadioMenuItem("Option");

// Groupe obligatoire pour exclusivité
ToggleGroup group = new ToggleGroup();
radioItem.setToggleGroup(group);

// Options spécifiques
radioItem.setSelected(true/false);              // Sélection initiale
radioItem.isSelected();                         // Vérifier l'état

// Création en boucle (pattern courant)
ToggleGroup group = new ToggleGroup();
String[] options = {"Option1", "Option2", "Option3"};
RadioMenuItem[] items = new RadioMenuItem[options.length];

for (int i = 0; i < options.length; i++) {
    items[i] = new RadioMenuItem(options[i]);
    items[i].setToggleGroup(group);
    menu.getItems().add(items[i]);
}
```

## CheckMenuItem (choix multiple)

### Syntaxe 
```java
// Création
CheckMenuItem checkItem = new CheckMenuItem("Option");

// Options spécifiques
checkItem.setSelected(true/false);              // État initial
checkItem.isSelected();                         // Vérifier l'état

// Création en boucle (pattern courant)
String[] options = {"Style1", "Style2", "Style3"};
CheckMenuItem[] items = new CheckMenuItem[options.length];

for (int i = 0; i < options.length; i++) {
    items[i] = new CheckMenuItem(options[i]);
    menu.getItems().add(items[i]);
}
```

## Sous-menus (Menu dans Menu)

```java
// Menu principal
Menu menuPrincipal = new Menu("Format");

// Sous-menu
Menu sousMenu = new Menu("Couleurs");
MenuItem item1 = new MenuItem("Rouge");
MenuItem item2 = new MenuItem("Bleu");

// Structure hiérarchique
sousMenu.getItems().addAll(item1, item2);
menuPrincipal.getItems().add(sousMenu);
```

## ➖ Séparateurs

```java
// Séparateur simple
menu.getItems().add(new SeparatorMenuItem());

// Pattern d'utilisation
menu.getItems().add(item1);
menu.getItems().add(item2);
menu.getItems().add(new SeparatorMenuItem());  // Ligne de séparation
menu.getItems().add(item3);
```

## Raccourcis clavier

### Syntaxe
```java
// Méthodes de création
item.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
item.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));

// Exemples courants
"Ctrl+C", "Ctrl+V", "Ctrl+X"           // Copier, Coller, Couper
"Ctrl+S", "Ctrl+O", "Ctrl+N"           // Sauvegarder, Ouvrir, Nouveau
"Ctrl+Q", "Alt+F4"                     // Quitter
"F1", "F2", "Delete"                   // Touches fonctions
"Ctrl+Shift+S"                         // Combinaisons multiples
"Alt+A"                                // Accès par Alt
```

## Gestionnaires d'événements


### 1. Classe anonyme
```java
item.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        // Code à exécuter
    }
});
```

### 2. Classe interne privée (pour logique complexe)
```java
private class GestionMenu implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        // Identifier la source
        MenuItem source = (MenuItem) event.getSource();
        String itemText = source.getText();
        
        // Traitement selon l'item
        switch (itemText) {
            case "Option1":
                // Actions pour Option1
                break;
            case "Option2":
                // Actions pour Option2
                break;
        }
    }
}

// Application du gestionnaire
item.setOnAction(new GestionMenu());
```

### 3. Méthode de référence
```java
// Si vous avez une méthode existante
item.setOnAction(this::maMethode);

private void maMethode(ActionEvent event) {
    // Code
}
```

## Boîtes de dialogue Alert

### Types disponibles
```java
// Types d'Alert
AlertType.INFORMATION    // Information (icône i)
AlertType.WARNING       // Avertissement (icône !)
AlertType.ERROR         // Erreur (icône X)
AlertType.CONFIRMATION  // Confirmation (icône ?)
```

### Syntaxe générique
```java
Alert alert = new Alert(AlertType.INFORMATION);

// Options principales
alert.setTitle("Titre de la fenêtre");
alert.setHeaderText("Texte d'en-tête");        // null pour masquer
alert.setContentText("Message principal");

// Méthodes d'affichage
alert.show();                    // Non-bloquant
alert.showAndWait();            // Bloquant (recommandé)

// Récupérer la réponse (pour CONFIRMATION)
Optional<ButtonType> result = alert.showAndWait();
if (result.isPresent() && result.get() == ButtonType.OK) {
    // Action si OK pressé
}
```

## Identification des sources d'événements

### Patterns courants
```java
@Override
public void handle(ActionEvent event) {
    // 1. Par référence directe
    if (event.getSource() == monMenuItem) {
        // Actions spécifiques
    }
    
    // 2. Par casting et texte
    MenuItem source = (MenuItem) event.getSource();
    String texte = source.getText();
    
    // 3. Par switch sur le texte
    switch (texte) {
        case "Copier": /* action */ break;
        case "Coller": /* action */ break;
    }
    
    // 4. Pour RadioMenuItem
    if (source instanceof RadioMenuItem) {
        RadioMenuItem radio = (RadioMenuItem) source;
        if (radio.isSelected()) {
            // Actions si sélectionné
        }
    }
    
    // 5. Pour CheckMenuItem
    if (source instanceof CheckMenuItem) {
        CheckMenuItem check = (CheckMenuItem) source;
        boolean isChecked = check.isSelected();
    }
}
```

## Méthodes utiles

### MenuItem communes
```java
item.getText()                    // Récupérer le texte
item.setText("Nouveau texte")     // Modifier le texte
item.isDisable()                  // État désactivé
item.setDisable(boolean)          // Activer/désactiver
item.isVisible()                  // État visible
item.setVisible(boolean)          // Montrer/cacher
```

### RadioMenuItem spécifiques
```java
radioItem.isSelected()            // État sélectionné
radioItem.setSelected(boolean)    // Sélectionner
radioItem.getToggleGroup()        // Récupérer le groupe
```

### CheckMenuItem spécifiques
```java
checkItem.isSelected()            // État coché
checkItem.setSelected(boolean)    // Cocher/décocher
```

### Menu spécifiques
```java
menu.getItems()                   // Liste des éléments
menu.getItems().add(item)         // Ajouter un élément
menu.getItems().clear()           // Vider le menu
menu.getItems().remove(item)      // Supprimer un élément
```


## Patterns de conception courants

### 1. Menu avec actions standards
```java
Menu menuFichier = new Menu("Fichier");
MenuItem nouveau = new MenuItem("Nouveau");
MenuItem ouvrir = new MenuItem("Ouvrir");
MenuItem sauvegarder = new MenuItem("Sauvegarder");
menuFichier.getItems().addAll(nouveau, ouvrir, new SeparatorMenuItem(), sauvegarder);
```

### 2. Groupe de RadioMenuItem
```java
ToggleGroup groupe = new ToggleGroup();
String[] options = {"Option1", "Option2", "Option3"};
for (String option : options) {
    RadioMenuItem item = new RadioMenuItem(option);
    item.setToggleGroup(groupe);
    menu.getItems().add(item);
}
```

### 3. CheckMenuItem pour styles
```java
String[] styles = {"Gras", "Italique", "Souligné"};
CheckMenuItem[] styleItems = new CheckMenuItem[styles.length];
for (int i = 0; i < styles.length; i++) {
    styleItems[i] = new CheckMenuItem(styles[i]);
    menu.getItems().add(styleItems[i]);
}
```