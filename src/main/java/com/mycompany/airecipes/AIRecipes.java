/*
Allows the user to receive a list of the Recipes with certain criteria they may want recipes to follow.
*/
package com.mycompany.airecipes;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Edwar
 */

//TODO: EVERYTHING FOR HAS TO BE CASE SENSITIVE
public class AIRecipes extends javax.swing.JFrame {

    private Gson gson;
    private String food;
    private String resultNum;
    
    private DefaultListModel exclCuisDLM;
    private DefaultListModel allCuisinesDLM;
    private String inclCuisines;
    private String excludedCuisines;
    
    private String diet;
    private DefaultListModel allDietsDLM;
    private DefaultListModel addedDietsDLM;
    
    private String intolerances;
    private DefaultListModel allIntolsDLM;
    private DefaultListModel addedIntolsDLM;
    
    private String type;
    private String apiKey;
    private String maxTime;
    private String sortCrit;
    private String sortDir;
    
    private String allNutritions;
    private HashMap<String, HashMap<String, String>> nutritionMap;

    /**
     * Creates new AIRecipes form, initializes components such as buttons and list models.
     */
    public AIRecipes() {
        initComponents();
   
        this.gson = new Gson();
        this.food = "";
        this.resultNum = "&number=" + resultNumSpin.getValue();
        
        this.inclCuisines = "&cuisine=";
        for(int i = 0; i < allCuisinesJList.getModel().getSize(); i++){
            inclCuisines = inclCuisines + allCuisinesJList.getModel().getElementAt(i) + ",";
        }
        this.excludedCuisines = "&excludeCuisine=";
        this.exclCuisDLM = new DefaultListModel();
        this.allCuisinesDLM = new DefaultListModel();
        for(int i = 0; i < allCuisinesJList.getModel().getSize(); i++){
            allCuisinesDLM.addElement(allCuisinesJList.getModel().getElementAt(i));
        }
        
        this.diet = "&diet=";
        this.allDietsDLM = new DefaultListModel();
        for(int i = 0; i < allDietsJList.getModel().getSize(); i++){
            allDietsDLM.addElement(allDietsJList.getModel().getElementAt(i));
        }
        this.addedDietsDLM = new DefaultListModel();
        
        this.intolerances = "&intolerances=";
        this.allIntolsDLM = new DefaultListModel();
        for(int i = 0; i < allIntolsJList.getModel().getSize(); i++){
            allIntolsDLM.addElement(allIntolsJList.getModel().getElementAt(i));
        }
        this.addedIntolsDLM = new DefaultListModel();
        
        this.type = "";
        
        this.apiKey = "&apiKey=" + apiTextField.getText();
        
        this.maxTime = "&maxReadyTime=" + prepTime.getValue();
        
        this.sortCrit = "&sort=" + sortCriteriaBox.getItemAt(sortCriteriaBox.getSelectedIndex());
        
        this.sortDir = "&sortdirection=asc";
        
        this.allNutritions = "";
        
        this.nutritionMap = new HashMap<String, HashMap<String, String>>();
        
        for (int i = 0; i < nutritionTable.getRowCount(); i++){
            HashMap<String, String> innerMap = new HashMap<String, String>();
            String factor = (String)nutritionTable.getValueAt(i, 0);
            innerMap.put("min" + factor.replace(" ", ""), null);
            innerMap.put("max" + factor.replace(" ", ""), null);
            this.nutritionMap.put(factor, innerMap);
        }
        
        nutritionTable.getModel().addTableModelListener(new TableModelListener(){ //uses abstract class
            @Override
            public void tableChanged(TableModelEvent e){
                int curRow = nutritionTable.getSelectedRow();
                int curCol = nutritionTable.getSelectedColumn();
                
                System.out.println(nutritionTable.getValueAt(curRow, curCol));
                
                String nutFactor = (String)nutritionTable.getValueAt(curRow, 0);
                
                HashMap<String, String> nutMap = nutritionMap.get(nutFactor);
                
                nutFactor = nutFactor.replace(" ", "");
                
                Object val = nutritionTable.getValueAt(curRow, curCol);
                
                String stringVal = "";
                
                if(curCol == 1){
                    if(val != null){
                        stringVal = val.toString();
                    }
                    else{
                        stringVal = null;
                    }
                    nutMap.put("min"+nutFactor.replace(" ", ""), stringVal);
                }
                else{
                    if(val != null){
                        stringVal = val.toString();
                    }
                    else{
                        stringVal = null;
                    }
                    nutMap.put("max"+nutFactor.replace(" ", ""), stringVal);
                }
                
                //TODO: add in logic so that user cannot add in value in min column that is larger than that in max column
                
                
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        typeButtonGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        desiredFood = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        allCuisinesJList = new javax.swing.JList<>();
        excludeCuisineButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        allIntolsJList = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        intolCommit = new javax.swing.JButton();
        mainCourseRadio = new javax.swing.JRadioButton();
        sideDishRadio = new javax.swing.JRadioButton();
        dessertRadio = new javax.swing.JRadioButton();
        appRadio = new javax.swing.JRadioButton();
        saladRadio = new javax.swing.JRadioButton();
        breadRadio = new javax.swing.JRadioButton();
        breakfastRadio = new javax.swing.JRadioButton();
        soupRadio = new javax.swing.JRadioButton();
        beverRadio = new javax.swing.JRadioButton();
        sauceRadio = new javax.swing.JRadioButton();
        marinadeRadio = new javax.swing.JRadioButton();
        fingerfoodRadio = new javax.swing.JRadioButton();
        snackRadio = new javax.swing.JRadioButton();
        drinkRadio = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        aiButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        sortToggle = new javax.swing.JToggleButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        nutritionTable = new javax.swing.JTable();
        apiTextField = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        excludedCuisinesJList = new javax.swing.JList<>();
        jScrollPane8 = new javax.swing.JScrollPane();
        addedIntolsJList = new javax.swing.JList<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        allDietsJList = new javax.swing.JList<>();
        dietCombo = new javax.swing.JComboBox<>();
        dietCommit = new javax.swing.JButton();
        sortCriteriaBox = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        addedDietsJList = new javax.swing.JList<>();
        prepTime = new javax.swing.JSpinner();
        resultNumSpin = new javax.swing.JSpinner();
        cuisineComboBox = new javax.swing.JComboBox<>();
        intolsCombo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        prepTimeCheck = new javax.swing.JCheckBox();
        sortCritCheck = new javax.swing.JCheckBox();
        sortDirCheck = new javax.swing.JCheckBox();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Welcome to TechnicallyCooking");

        searchButton.setText("Search Recipes");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("How many food types?");

        jLabel3.setText("Cuisine Types:");

        allCuisinesJList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "African", "American", "British", "Cajun", "Caribbean", "Chinese", "Eastern European", "European", "French", "German", "Greek", "Indian", "Irish", "Italian", "Japanese", "Jewish", "Korean", "Latin American", "Mediterranean", "Mexican", "Middle Eastern", "Nordic", "Southern", "Spanish", "Thai", "Vietnamese" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(allCuisinesJList);

        excludeCuisineButton.setText("Commit");
        excludeCuisineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excludeCuisineButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Dietary Restrictions:");

        allIntolsJList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Dairy", "Egg", "Gluten", "Grain", "Peanut", "Seafood", "Sesame", "Shellfish", "Soy", "Sulfite", "Treet Nut", "Wheat" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(allIntolsJList);

        jLabel5.setText("Intolerances: ");

        intolCommit.setText("Commit");
        intolCommit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intolCommitActionPerformed(evt);
            }
        });

        typeButtonGroup.add(mainCourseRadio);
        mainCourseRadio.setText("Main Course");

        typeButtonGroup.add(sideDishRadio);
        sideDishRadio.setText("Side Dish");

        typeButtonGroup.add(dessertRadio);
        dessertRadio.setText("Dessert");

        typeButtonGroup.add(appRadio);
        appRadio.setText("Appetizer");

        typeButtonGroup.add(saladRadio);
        saladRadio.setText("Salad");

        typeButtonGroup.add(breadRadio);
        breadRadio.setText("Bread");

        typeButtonGroup.add(breakfastRadio);
        breakfastRadio.setText("Breakfast");

        typeButtonGroup.add(soupRadio);
        soupRadio.setText("Soup");

        typeButtonGroup.add(beverRadio);
        beverRadio.setText("Beverage");

        typeButtonGroup.add(sauceRadio);
        sauceRadio.setText("Sauce");

        typeButtonGroup.add(marinadeRadio);
        marinadeRadio.setText("Marinade");

        typeButtonGroup.add(fingerfoodRadio);
        fingerfoodRadio.setText("Fingerfood");

        typeButtonGroup.add(snackRadio);
        snackRadio.setText("Snack");

        typeButtonGroup.add(drinkRadio);
        drinkRadio.setText("Drink");

        jLabel6.setText("Maximum Preparation Time (In Minutes):");

        aiButton.setText("Use AI To Generate Food");
        aiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aiButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("Sort Criteria");

        jLabel8.setText("Sort Direction");

        sortToggle.setText("Ascending");
        sortToggle.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sortToggleItemStateChanged(evt);
            }
        });

        nutritionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Carbohydrates", null, null, "grams"},
                {"Protein", null, null, "grams"},
                {"Calories", null, null, "calories"},
                {"Fat", null, null, "grams"},
                {"Alcohol", null, null, "grams"},
                {"Caffeine", null, null, "milligrams"},
                {"Copper", null, null, "milligrams"},
                {"Calcium", null, null, "milligrams"},
                {"Choline", null, null, "milligrams"},
                {"Cholesterol", null, null, "milligrams"},
                {"Fluoride", null, null, "milligrams"},
                {"Saturated Fat", null, null, "grams"},
                {"Vitamin A", null, null, "International Unit"},
                {"Vitamin C", null, null, "milligrams"},
                {"Vitamin D", null, null, "micrograms"},
                {"Vitamin E", null, null, "milligrams"},
                {"Vitamin K", null, null, "micrograms"},
                {"Vitamin B1", null, null, "milligrams"},
                {"Vitamin B2", null, null, "milligrams"},
                {"Vitamin B3", null, null, "milligrams"},
                {"Vitamin B5", null, null, "milligrams"},
                {"Vitamin B6", null, null, "milligrams"},
                {"Vitamin B12", null, null, "micrograms"},
                {"Fiber", null, null, "grams"},
                {"Folate", null, null, "micrograms"},
                {"Folic Acid", null, null, "micrograms"},
                {"Iodine", null, null, "micrograms"},
                {"Iron", null, null, "milligrams"},
                {"Magnesium", null, null, "milligrams"},
                {"Maganese", null, null, "milligrams"},
                {"Phosphorus", null, null, "milligrams"},
                {"Potassium", null, null, "milligrams"},
                {"Selenium", null, null, "micrograms"},
                {"Sodium", null, null, "milligrams"},
                {"Sugar", null, null, "grams"},
                {"Zinc", null, null, "milligrams"}
            },
            new String [] {
                "Nutrition Factor", "Minimum", "Maximum", "Measurement"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(nutritionTable);
        if (nutritionTable.getColumnModel().getColumnCount() > 0) {
            nutritionTable.getColumnModel().getColumn(0).setResizable(false);
            nutritionTable.getColumnModel().getColumn(1).setResizable(false);
            nutritionTable.getColumnModel().getColumn(2).setResizable(false);
            nutritionTable.getColumnModel().getColumn(3).setResizable(false);
        }

        apiTextField.setText("ecf813497d49484187d848b720e3baff");
        apiTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                apiTextFieldFocusLost(evt);
            }
        });

        jScrollPane6.setViewportView(excludedCuisinesJList);

        jScrollPane8.setViewportView(addedIntolsJList);

        jLabel9.setText("Please enter the type of food you want to eat.");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        allDietsJList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Gluten Free", "Ketogenic", "Vegetarian", "Lacto-Vegetarian", "Ovo-Vegetarian", "Vegan", "Pescetarian", "Paleo", "Primal", "Low FODMAP", "Whole30" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(allDietsJList);

        dietCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Add (Inclusive)", "Add (Exclusive)", "Remove" }));

        dietCommit.setText("Commit");
        dietCommit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dietCommitActionPerformed(evt);
            }
        });

        sortCriteriaBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Meta-score", "Popularity", "Healthiness", "Price", "Time", "Random", "Max-used-ingredients", "Min-missing-ingredients", "Alcohol", "Caffeine", "Copper", "Energy", "Calroies", "Calcium", "Carbohydrates", "Carbs", "Choline", "Cholesterol", "Total-fat", "Fluoride", "Trans-fat", "Saturated-fat", "Mono-unsaturated-fat", "Poly-unsaturated-fat", "Fiber", "Folate", "Folic-acid", "Iodine", "Iron", "Magnesium", "Maganese", "Vitamin-B3", "Niacin", "Vitamin-B5", "Pantothenic-acid", "Phosphorus", "Potassium", "Protein", "Vitamin-B2", "Riboflavin", "Selenium", "Sodium", "Vitamin-B1", "Thiamin", "Vitamin-A", "Vitamin-B6", "Vitamin-B12", "Vitamin-C", "Vitamin-D", "Vitamin-D", "Vitamin-E", "Vitamin-K", "Sugar", "Zinc" }));
        sortCriteriaBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortCriteriaBoxActionPerformed(evt);
            }
        });

        jScrollPane7.setViewportView(addedDietsJList);

        prepTime.setModel(new javax.swing.SpinnerNumberModel(60, 5, null, 1));
        prepTime.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                prepTimeFocusLost(evt);
            }
        });

        resultNumSpin.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        resultNumSpin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                resultNumSpinFocusLost(evt);
            }
        });

        cuisineComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Add to Excluded Cuisines", "Add to Included Cuisines" }));

        intolsCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Add", "Remove" }));

        jLabel10.setText("API Key");

        prepTimeCheck.setSelected(true);
        prepTimeCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prepTimeCheckActionPerformed(evt);
            }
        });

        sortCritCheck.setSelected(true);
        sortCritCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortCritCheckActionPerformed(evt);
            }
        });

        sortDirCheck.setSelected(true);
        sortDirCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortDirCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(resultNumSpin, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(desiredFood, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(aiButton))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(55, 55, 55)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(excludeCuisineButton)
                                            .addComponent(cuisineComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(intolsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(intolCommit)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(185, 185, 185)
                                .addComponent(jLabel15))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dietCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dietCommit))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(365, 365, 365)
                                        .addComponent(searchButton))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(373, 373, 373)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(sideDishRadio)
                                            .addComponent(mainCourseRadio)
                                            .addComponent(dessertRadio)
                                            .addComponent(appRadio)
                                            .addComponent(saladRadio))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(beverRadio)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(drinkRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(sauceRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(soupRadio)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(snackRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(breadRadio)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(marinadeRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(breakfastRadio)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(fingerfoodRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(597, 597, 597)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(40, 40, 40)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(apiTextField)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(sortCritCheck)
                                    .addComponent(prepTimeCheck)
                                    .addComponent(sortDirCheck))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(sortToggle))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(sortCriteriaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(prepTime, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 9, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(desiredFood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aiButton)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(435, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(apiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(prepTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(prepTimeCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(sortCriteriaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sortCritCheck))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(sortDirCheck)
                                    .addComponent(sortToggle, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(searchButton)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(resultNumSpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cuisineComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(excludeCuisineButton)))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(dietCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dietCommit))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(intolsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(intolCommit)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(mainCourseRadio)
                                    .addComponent(breadRadio)
                                    .addComponent(marinadeRadio))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(sideDishRadio)
                                    .addComponent(breakfastRadio)
                                    .addComponent(fingerfoodRadio))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dessertRadio)
                                        .addComponent(soupRadio)
                                        .addComponent(snackRadio))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(appRadio)
                                            .addComponent(beverRadio)
                                            .addComponent(drinkRadio))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(sauceRadio)
                                            .addComponent(saladRadio))))
                                .addGap(108, 108, 108)))
                        .addGap(0, 65, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /*
    Is triggered whenever the searchButton is clicked, and this will call the Spoonacular API using the different link sections containing criteria for the API
    @params java.awt.event.ActionEvent evt: an action occurs within the page
    */
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        //https://stackoverflow.com/questions/201287/how-do-i-get-which-jradiobutton-is-selected-from-a-buttongroup
        for (Enumeration<AbstractButton> buttons = typeButtonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                type = "&type=" + button.getText();
                break;
            }
        }
        
        food = "query=" + desiredFood.getText();
        
        if(exclCuisDLM.isEmpty()){
            excludedCuisines = "";
        }
        
        if(addedDietsDLM.isEmpty()){
            diet = "";
        }
        
        if(addedIntolsDLM.isEmpty()){
            intolerances = "";
        }
        
        Set<String> keys = nutritionMap.keySet();
        
        for(String key:keys){
            HashMap<String, String> curInner = nutritionMap.get(key);
            Set<String> maxMinKeys = curInner.keySet();
            
            for(String innerKey:maxMinKeys){
                //if key value is not null, add the key name 
                if(curInner.get(innerKey) != null){
                    allNutritions = allNutritions + "&" + innerKey + "=" + curInner.get(innerKey);
                }
            } 
        }
        
        if(inclCuisines.endsWith(",")){
           inclCuisines = StringUtils.chop(inclCuisines);
        }
        
        inclCuisines = inclCuisines.replaceAll(" ", "_");
        
        System.out.println("https://api.spoonacular.com/recipes/complexSearch?" + food + resultNum + inclCuisines + excludedCuisines + diet + intolerances + type + maxTime + sortCrit + sortDir + allNutritions + apiKey);
        
        try{
            URL url = new URL("https://api.spoonacular.com/recipes/complexSearch?" + food + resultNum + inclCuisines + excludedCuisines + diet + intolerances + type + maxTime + sortCrit + sortDir + allNutritions + apiKey);
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
               
            connection.connect();
            System.out.println("1");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println("2");
            Food foodJson = gson.fromJson(reader, Food.class);
            System.out.println(foodJson.getNumber());
            int result = foodJson.getResults().get(0).getResultId();
            System.out.println(result);
            System.out.println(url);
            
        } catch(MalformedURLException ex){System.out.println("nah");} //TODO: include error message
        catch(IOException io){System.out.println(io);}
    }//GEN-LAST:event_searchButtonActionPerformed

    
    /*
    Removes the currently selected item in the allCusiines JList, and adds the same item to the excludedCuisines JList as long as that item is not already in the JList.
    @params java.awt.event.ActionEvent evt: an action occurs to that form aspect
    */
    private void excludeCuisineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excludeCuisineButtonActionPerformed
        if(allCuisinesJList.isSelectionEmpty() == false && cuisineComboBox.getSelectedIndex() == 0){

            excludedCuisines = excludedCuisines + allCuisinesJList.getSelectedValue() + ",";
            inclCuisines = inclCuisines.replace(allCuisinesJList.getSelectedValue() + ",", "");
            exclCuisDLM.addElement(allCuisinesJList.getSelectedValue());
            excludedCuisinesJList.setModel(exclCuisDLM);

            allCuisinesDLM.removeElement(allCuisinesJList.getSelectedValue());
            allCuisinesJList.setModel(allCuisinesDLM);

            System.out.println(excludedCuisines);
            System.out.println(inclCuisines);
        }
        else if(excludedCuisinesJList.isSelectionEmpty() == false && cuisineComboBox.getSelectedIndex() == 1){
            inclCuisines = inclCuisines + excludedCuisinesJList.getSelectedValue() + ",";
            excludedCuisines = excludedCuisines.replace(excludedCuisinesJList.getSelectedValue() + ",", "");
            allCuisinesDLM.addElement(excludedCuisinesJList.getSelectedValue());
            allCuisinesJList.setModel(allCuisinesDLM);

            exclCuisDLM.removeElement(excludedCuisinesJList.getSelectedValue());
            excludedCuisinesJList.setModel(exclCuisDLM);

            System.out.println(excludedCuisines);
            System.out.println(inclCuisines);
        }
        
    }//GEN-LAST:event_excludeCuisineButtonActionPerformed

    /*
    Removes the currently selected item in the allIntols JList, and adds the same item to the addedIntols JList as long as that item is not already in the JList.
    @params java.awt.event.ActionEvent evt: an action occurs to that form aspect
    */
    private void intolCommitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intolCommitActionPerformed
        if (intolsCombo.getSelectedIndex() == 0){
            addedIntolsDLM.addElement(allIntolsJList.getSelectedValue());
            addedIntolsJList.setModel(addedIntolsDLM);
            intolerances = intolerances + allIntolsJList.getSelectedValue() + ",";

            allIntolsDLM.removeElement(allIntolsJList.getSelectedValue());
            allIntolsJList.setModel(allIntolsDLM);
        }
        
        else if(intolsCombo.getSelectedIndex() == 1){
            allIntolsDLM.addElement(addedIntolsJList.getSelectedValue());
            allIntolsJList.setModel(allIntolsDLM);
            intolerances = intolerances.replace(addedIntolsJList.getSelectedValue() + ",", "");

            addedIntolsDLM.removeElement(addedIntolsJList.getSelectedValue());
            addedIntolsJList.setModel(addedIntolsDLM);
        }
    }//GEN-LAST:event_intolCommitActionPerformed

    /*
    Will soon contain logic to ask the user for a food type they desire (ex. pasta) and then return a food type they may also want based off of that response (ex. chicken ziti broccoli)
    @params java.awt.event.ActionEvent evt: an action occurs to that form aspect
    */
    private void aiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aiButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aiButtonActionPerformed
    
    /*
    Changes the display text of sortToggle toggle button and value of sortDir based off of the new display text
    @params java.awt.event.ItemEvent evt: an action occurs to that form aspect
    */
    private void sortToggleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sortToggleItemStateChanged
        if(sortDirCheck.isSelected()){
            if(sortToggle.isSelected()){
                sortToggle.setText("Descending");
                sortDir = "&sortdirection=desc";
            }
            else{
                sortToggle.setText("Ascending");
                sortDir = "&sortdirection=asc";
            }
        }
        System.out.println(sortDir);
    }//GEN-LAST:event_sortToggleItemStateChanged

   /*
    When the person is no longer using the resultNumSpin JSpinner, the resultNum field will be changed to the new value
    @params java.awt.event.FocusEvent: Focus on this form aspect is lost (no longer selected)
    */
    private void resultNumSpinFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_resultNumSpinFocusLost
        resultNum = "&number=" + resultNumSpin.getValue().toString();
    }//GEN-LAST:event_resultNumSpinFocusLost

    /*
    Once the dietCommit button is clicked, the allDiets JList will remove the currently selected item and that same item will be added to the addedDiets JList
    @params java.awt.event.ActionEvent evt: an action occurs to that form aspect
    */
    private void dietCommitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dietCommitActionPerformed
        if(dietCombo.getSelectedIndex() == 0){
            diet = diet + allDietsJList.getSelectedValue() + "|";
            
            addedDietsDLM.addElement(allDietsJList.getSelectedValue());
            addedDietsJList.setModel(addedDietsDLM);
            
            allDietsDLM.removeElement(allDietsJList.getSelectedValue());
            allDietsJList.setModel(allDietsDLM);
        }
        
        else if(dietCombo.getSelectedIndex() == 1){
            diet = diet + allDietsJList.getSelectedValue() + ",";
            
            addedDietsDLM.addElement(allDietsJList.getSelectedValue());
            addedDietsJList.setModel(addedDietsDLM);
            
            allDietsDLM.removeElement(allDietsJList.getSelectedValue());
            allDietsJList.setModel(allDietsDLM);
        }
        
        else{
            int delIndex = diet.indexOf(addedDietsJList.getSelectedValue());
            delIndex = delIndex + addedDietsJList.getSelectedValue().length();
            
            if(delIndex == diet.length() - 1){
                diet = diet.substring(0,delIndex);
            }
            else{
                diet = diet.substring(0, delIndex) + diet.substring(delIndex + 1);
            }
            
            diet = diet.replace(addedDietsJList.getSelectedValue(), "");
            allDietsDLM.addElement(addedDietsJList.getSelectedValue());
            allDietsJList.setModel(allDietsDLM);
            
            addedDietsDLM.removeElement(addedDietsJList.getSelectedValue());
            addedDietsJList.setModel(addedDietsDLM);
        }
        
        System.out.println(diet);
    }//GEN-LAST:event_dietCommitActionPerformed

    /*
    Based off of the value in the apiTextField, the value of apiKey will be chanegd to that value
    @params java.awt.event.FocusEvent: Focus on this form aspect is lost (no longer selected)
    */
    private void apiTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_apiTextFieldFocusLost
        apiKey = "&apiKey=" + apiTextField.getText();
    }//GEN-LAST:event_apiTextFieldFocusLost
    /*
    Based off of the current value of the prepTime JSpinner, the maxTime field will be changed to that value
    @params java.awt.event.FocusEvent: Focus on this form aspect is lost (no longer selected)
    */
    private void prepTimeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_prepTimeFocusLost
        maxTime = "&maxReadyTime=" + prepTime.getValue().toString();
    }//GEN-LAST:event_prepTimeFocusLost

    /*
    The sortCrit field will be changed to the newly selected value within the sortCriteria JComboBox
    @params java.awt.event.ActionEvent: An action occurs on the currently designated form aspect
    */
    private void sortCriteriaBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortCriteriaBoxActionPerformed
        sortCrit = "&sort=" + sortCriteriaBox.getItemAt(sortCriteriaBox.getSelectedIndex());
    }//GEN-LAST:event_sortCriteriaBoxActionPerformed

    private void sortDirCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortDirCheckActionPerformed
        if(sortDirCheck.isSelected() == false){
            sortDir = "";
            sortToggle.setVisible(false);
        }
        else{
            sortToggle.setVisible(true);
            if(sortToggle.isSelected()){
                sortToggle.setText("Descending");
                sortDir = "&sortdirection=desc";
            }
            else{
                sortToggle.setText("Ascending");
                sortDir = "&sortdirection=asc";
            }
        }
    }//GEN-LAST:event_sortDirCheckActionPerformed

    private void sortCritCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortCritCheckActionPerformed
        if(sortCritCheck.isSelected() == false){
            sortCrit = "";
            sortCriteriaBox.setVisible(false);
        }
        else{
            sortCriteriaBox.setVisible(true);
            sortCrit = "&sort=" + sortCriteriaBox.getItemAt(sortCriteriaBox.getSelectedIndex());
        }
    }//GEN-LAST:event_sortCritCheckActionPerformed

    private void prepTimeCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prepTimeCheckActionPerformed
        if(prepTimeCheck.isSelected() == false){
            maxTime = "";
            prepTime.setVisible(false);
        }
        else{
            maxTime = "&maxReadyTime=" + prepTime.getValue();
            prepTime.setVisible(true);
        }
    }//GEN-LAST:event_prepTimeCheckActionPerformed

    /**
     * Launches the AIRecipes Page
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AIRecipes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AIRecipes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AIRecipes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AIRecipes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AIRecipes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> addedDietsJList;
    private javax.swing.JList<String> addedIntolsJList;
    private javax.swing.JButton aiButton;
    private javax.swing.JList<String> allCuisinesJList;
    private javax.swing.JList<String> allDietsJList;
    private javax.swing.JList<String> allIntolsJList;
    private javax.swing.JTextField apiTextField;
    private javax.swing.JRadioButton appRadio;
    private javax.swing.JRadioButton beverRadio;
    private javax.swing.JRadioButton breadRadio;
    private javax.swing.JRadioButton breakfastRadio;
    private javax.swing.JComboBox<String> cuisineComboBox;
    private javax.swing.JTextField desiredFood;
    private javax.swing.JRadioButton dessertRadio;
    private javax.swing.JComboBox<String> dietCombo;
    private javax.swing.JButton dietCommit;
    private javax.swing.JRadioButton drinkRadio;
    private javax.swing.JButton excludeCuisineButton;
    private javax.swing.JList<String> excludedCuisinesJList;
    private javax.swing.JRadioButton fingerfoodRadio;
    private javax.swing.JButton intolCommit;
    private javax.swing.JComboBox<String> intolsCombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JRadioButton mainCourseRadio;
    private javax.swing.JRadioButton marinadeRadio;
    private javax.swing.JTable nutritionTable;
    private javax.swing.JSpinner prepTime;
    private javax.swing.JCheckBox prepTimeCheck;
    private javax.swing.JSpinner resultNumSpin;
    private javax.swing.JRadioButton saladRadio;
    private javax.swing.JRadioButton sauceRadio;
    private javax.swing.JButton searchButton;
    private javax.swing.JRadioButton sideDishRadio;
    private javax.swing.JRadioButton snackRadio;
    private javax.swing.JCheckBox sortCritCheck;
    private javax.swing.JComboBox<String> sortCriteriaBox;
    private javax.swing.JCheckBox sortDirCheck;
    private javax.swing.JToggleButton sortToggle;
    private javax.swing.JRadioButton soupRadio;
    private javax.swing.ButtonGroup typeButtonGroup;
    // End of variables declaration//GEN-END:variables
}
