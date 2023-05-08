/*
Allows the user to receive a list of the Recipes with certain criteria they may want recipes to follow.
*/
package airecipes;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Edwar
 */

public class AIRecipes extends javax.swing.JFrame{

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
    
    private RecipeBook recipeBook;

    /**
     * Creates new AIRecipes form, initializes components such as buttons and list models.
     */
    public AIRecipes() {
        initComponents();
   
        this.gson = new Gson();
        this.food = "";
        this.resultNum = "";
        
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
        
        this.apiKey = "";
        
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
                
                String nutFactor = (String)nutritionTable.getValueAt(curRow, 0);
                
                HashMap<String, String> nutMap = nutritionMap.get(nutFactor);
                
                nutFactor = nutFactor.replace(" ", "");
                
                Object val = nutritionTable.getValueAt(curRow, curCol);
                
                String stringVal = "";
                
                if(val != null){
                    stringVal = val.toString();
                }
                else{
                    stringVal = null;
                }
                
                if(curCol == 1){
                    if(stringVal != null && nutritionTable.getValueAt(curRow, curCol + 1) != null && Integer.parseInt(stringVal) > Integer.parseInt(nutritionTable.getValueAt(curRow, curCol + 1).toString())){
                        nutritionTable.setValueAt(null, curRow, curCol);
                        stringVal = null;
                    }
                    nutMap.put("min"+nutFactor.replace(" ", ""), stringVal);
                }
                else{
                    if(stringVal != null && nutritionTable.getValueAt(curRow, curCol - 1) != null && Integer.parseInt(stringVal) < Integer.parseInt(nutritionTable.getValueAt(curRow, curCol - 1).toString())){
                        nutritionTable.setValueAt(null, curRow, curCol);
                        stringVal = null;
                    }
                    nutMap.put("max"+nutFactor.replace(" ", ""), stringVal);
                }   
            }
        });
    }
    
    
    /**
     * 
     * @param formString - desired string to be formatted
     * Formats the input string in order to remove spaces for API use
     * @return formattedString
     */
    public String formatSpec(String formString){
        String formattedString = formString.replace(" ", "%20");
        
        if(formattedString.endsWith(",") || formattedString.endsWith("|")){
            formattedString = StringUtils.chop(formattedString);
        }
        
        return formattedString;
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
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

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

        jLabel2.setText("How many results do you want?");

        jLabel3.setText("Cuisine Types");

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

        jLabel4.setText("Dietary Restrictions");

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
                {"Carbs", null, null, "grams"},
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

        apiTextField.setText("ed508c375cef4d68888fec78ff587562");

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

        cuisineComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Exclude Cuisine", "Include Cuisine" }));

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

        jLabel11.setText("All Diets");

        jLabel12.setText("Added Diets");

        jLabel13.setText("Excluded Cuisines");

        jLabel14.setText("All Intolerances");

        jLabel16.setText("Added Intolerances");

        jLabel17.setText("All Cuisines");

        jLabel18.setText("Food Categories");

        jLabel19.setText("Nutrition Factors");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sortDirCheck)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sortToggle))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(desiredFood, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(aiButton))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sortCritCheck)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(sortCriteriaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel18)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sideDishRadio)
                            .addComponent(mainCourseRadio)
                            .addComponent(dessertRadio)
                            .addComponent(appRadio)
                            .addComponent(saladRadio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(beverRadio)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(breadRadio)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(marinadeRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(soupRadio)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(breakfastRadio)
                                        .addComponent(fingerfoodRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sauceRadio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(drinkRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(snackRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(resultNumSpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(prepTimeCheck)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prepTime, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel11))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(jLabel12)
                                .addGap(55, 55, 55)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addGap(21, 21, 21))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(dietCommit))
                                            .addComponent(dietCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cuisineComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(excludeCuisineButton)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(292, 292, 292)
                                        .addComponent(jLabel3)))
                                .addGap(11, 11, 11))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14)
                                .addGap(139, 139, 139)
                                .addComponent(jLabel16))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(intolsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(intolCommit, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(212, 212, 212)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(167, 167, 167))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(626, 626, 626)
                        .addComponent(jLabel10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(searchButton))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(apiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(699, 699, 699)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 775, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel16))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(intolsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(intolCommit))
                                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel19))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel17)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel13)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane7)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cuisineComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(excludeCuisineButton))
                                    .addComponent(jScrollPane2)
                                    .addComponent(jScrollPane6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(dietCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dietCommit))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel15))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(desiredFood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(aiButton))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resultNumSpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(mainCourseRadio)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(sideDishRadio)
                                            .addComponent(soupRadio)
                                            .addComponent(fingerfoodRadio))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(dessertRadio)
                                                .addComponent(sauceRadio)
                                                .addComponent(drinkRadio))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(appRadio)
                                                    .addComponent(snackRadio)
                                                    .addComponent(breakfastRadio))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(saladRadio)
                                                    .addComponent(beverRadio)))))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(breadRadio)
                                        .addComponent(marinadeRadio)))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(prepTimeCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(prepTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sortCritCheck, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(sortCriteriaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(sortDirCheck)
                                    .addComponent(sortToggle, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(searchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * @param evt - An action occurs on the currently designated form aspect
     * Utilizes many pre-made variables and Swing elements to grab inputs from user to create a formatted call to the Spoonacular API
     */
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        if(nutritionTable.isEditing()){
            JOptionPane.showMessageDialog(null, "Please finish editing the Nutrition Factors table before searching. To finish entering your requirement, please press the ENTER key.", "Nutrition Factors Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(desiredFood.getText().isEmpty() || apiTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please finish the required fields in order to continue your search. The required fields include the API Key field and the type of food you desire to eat.", "Query Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //https://stackoverflow.com/questions/201287/how-do-i-get-which-jradiobutton-is-selected-from-a-buttongroup
        for (Enumeration<AbstractButton> buttons = typeButtonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                type = "&type=" + formatSpec(button.getText().toLowerCase());
                break;
            }
        }
        
        food = "query=" + desiredFood.getText().toLowerCase();
        
        food = food.replace(" ", "%20");
        
        resultNum = "&number=" + resultNumSpin.getValue().toString();
        
        apiKey = "&apiKey=" + apiTextField.getText();
        
        if(allCuisinesDLM.isEmpty()){
            inclCuisines = "";
        }
        else{
            inclCuisines = formatSpec(inclCuisines);
        }
        
        if(exclCuisDLM.isEmpty()){
            excludedCuisines = "";
        }
        else{
            excludedCuisines = formatSpec(excludedCuisines);
        }
        
        if(addedDietsDLM.isEmpty()){
            diet = "";
        }
        else{
            diet = formatSpec(diet);
        }
        
        if(addedIntolsDLM.isEmpty()){
            intolerances = "";
        }
        else{
            intolerances = formatSpec(intolerances);
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
        
        try{
            URL url = new URL("https://api.spoonacular.com/recipes/complexSearch?" + food + resultNum + inclCuisines + excludedCuisines + diet + intolerances + type + maxTime + sortCrit + sortDir + allNutritions + apiKey);
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
               
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Food foodJson = gson.fromJson(reader, Food.class);
            int result = foodJson.getAPIResults().get(0).getResultId();
            
            DefaultListModel foodTitles = new DefaultListModel();
            
            ArrayList<Result> resultList = foodJson.getAPIResults();
            
            for(int i = 0; i < resultList.size(); i++){
                foodTitles.add(i, resultList.get(i).getResultTitle());
            }
            
            this.recipeBook = new RecipeBook(this, resultList,foodTitles, this.apiKey);
            
            recipeBook.setVisible(true);
            
            this.setVisible(false);
            
        } catch(MalformedURLException ex){JOptionPane.showMessageDialog(null, "Some of the information you entered caused an error, please try again with new information.", "Nothing Returned", JOptionPane.ERROR_MESSAGE);}
        catch(IOException io){JOptionPane.showMessageDialog(null, "There was an error when trying to reach the Spoonacular database, please try again", "Nothing Returned", JOptionPane.ERROR_MESSAGE);}
        catch(IndexOutOfBoundsException except){
            JOptionPane.showMessageDialog(null, "The food type you included returned 0 results, please try another food type.", "Nothing Returned", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    
    /**
     * 
     * @param evt - An action occurs on the currently designated form aspect
     * Changes the cuisine JLists based off of what value is currently selected in the cuisine combo box
     */
    private void excludeCuisineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excludeCuisineButtonActionPerformed
        if(allCuisinesJList.isSelectionEmpty() == false && cuisineComboBox.getSelectedIndex() == 0){

            excludedCuisines = excludedCuisines + allCuisinesJList.getSelectedValue() + ",";
            if(inclCuisines.indexOf(allCuisinesJList.getSelectedValue() + ",") == inclCuisines.lastIndexOf(allCuisinesJList.getSelectedValue() + ",")){
                inclCuisines = inclCuisines.replace(allCuisinesJList.getSelectedValue() + ",", "");
            }
            
            else{
                inclCuisines = deleteStringPortion(inclCuisines, allCuisinesJList.getSelectedValue(), " ");
                if(inclCuisines.contains("&cuisine=") == false){
                    inclCuisines = "&cuisine=" + inclCuisines;
                }
            }
            
            exclCuisDLM.addElement(allCuisinesJList.getSelectedValue());
            excludedCuisinesJList.setModel(exclCuisDLM);

            allCuisinesDLM.removeElement(allCuisinesJList.getSelectedValue());
            allCuisinesJList.setModel(allCuisinesDLM);
        }
        else if(excludedCuisinesJList.isSelectionEmpty() == false && cuisineComboBox.getSelectedIndex() == 1){
            inclCuisines = inclCuisines + excludedCuisinesJList.getSelectedValue() + ",";
            
            if(excludedCuisines.indexOf(excludedCuisinesJList.getSelectedValue() + ",") == excludedCuisines.lastIndexOf(excludedCuisinesJList.getSelectedValue() + ",")){
                excludedCuisines = excludedCuisines.replace(excludedCuisinesJList.getSelectedValue() + ",", "");
            }
            
            else{
                excludedCuisines = deleteStringPortion(excludedCuisines, excludedCuisinesJList.getSelectedValue(), " ");
                if(excludedCuisines.contains("&excludeCuisine=") == false){
                    excludedCuisines = "&excludeCuisine=" + excludedCuisines;
                }
            }
            
            allCuisinesDLM.addElement(excludedCuisinesJList.getSelectedValue());
            allCuisinesJList.setModel(allCuisinesDLM);

            exclCuisDLM.removeElement(excludedCuisinesJList.getSelectedValue());
            excludedCuisinesJList.setModel(exclCuisDLM);
        }
        
    }//GEN-LAST:event_excludeCuisineButtonActionPerformed

    /**
     * Removes the currently selected item in the allIntols JList, and adds the same item to the addedIntols JList as long as that item is not already in the JList.
     * @param evt - an action occurs to that form aspect
     */
    private void intolCommitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intolCommitActionPerformed
        if(intolsCombo.getSelectedIndex() == 0){
                if(allIntolsJList.isSelectionEmpty() == false && addedIntolsJList.isSelectionEmpty()){
                addedIntolsDLM.addElement(allIntolsJList.getSelectedValue());
                addedIntolsJList.setModel(addedIntolsDLM);
                intolerances = intolerances + allIntolsJList.getSelectedValue() + ",";

                allIntolsDLM.removeElement(allIntolsJList.getSelectedValue());
                allIntolsJList.setModel(allIntolsDLM);
            }
        }
        
        else if(intolsCombo.getSelectedIndex() == 1){
            if(allIntolsJList.isSelectionEmpty() && addedIntolsJList.isSelectionEmpty() == false){
                allIntolsDLM.addElement(addedIntolsJList.getSelectedValue());
                allIntolsJList.setModel(allIntolsDLM);
                intolerances = intolerances.replace(addedIntolsJList.getSelectedValue() + ",", "");

                addedIntolsDLM.removeElement(addedIntolsJList.getSelectedValue());
                addedIntolsJList.setModel(addedIntolsDLM);
            }
        }
    }//GEN-LAST:event_intolCommitActionPerformed

    /**
     * Will soon contain logic to ask the user for a food type they desire (ex. pasta) and then return a food type they may also want based off of that response (ex. chicken ziti broccoli)
     * @param evt - an action occurs to that form aspect
     */
    private void aiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aiButtonActionPerformed
        AIEntry currentEntry = new AIEntry(this, true, this.gson);
        
        currentEntry.setVisible(true);
        
        desiredFood.setText(currentEntry.getResponse());
    }//GEN-LAST:event_aiButtonActionPerformed
    
    /**
     * Changes the display text of sortToggle toggle button and value of sortDir based off of the new display text
     * @param evt - an action occurs to that form aspect
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
    }//GEN-LAST:event_sortToggleItemStateChanged

    /**
     * Once the dietCommit button is clicked, the allDiets JList will remove the currently selected item and that same item will be added to the addedDiets JList
     * @param evt - an action occurs to that form aspect
     */
    private void dietCommitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dietCommitActionPerformed
        if(dietCombo.getSelectedIndex() == 0){
            if(allDietsJList.isSelectionEmpty() == false && addedDietsJList.isSelectionEmpty() == true){
                diet = diet + allDietsJList.getSelectedValue() + "|";
            
                addedDietsDLM.addElement(allDietsJList.getSelectedValue() + " (incl.)");
                addedDietsJList.setModel(addedDietsDLM);
            
                allDietsDLM.removeElement(allDietsJList.getSelectedValue());
                allDietsJList.setModel(allDietsDLM);
            }
        }
        
        else if(dietCombo.getSelectedIndex() == 1){
            if(allDietsJList.isSelectionEmpty() == false && addedDietsJList.isSelectionEmpty() == true){
                diet = diet + allDietsJList.getSelectedValue() + ",";
            
                addedDietsDLM.addElement(allDietsJList.getSelectedValue() + " (excl.)");
                addedDietsJList.setModel(addedDietsDLM);
            
                allDietsDLM.removeElement(allDietsJList.getSelectedValue());
                allDietsJList.setModel(allDietsDLM);
            }
        }
        
        else{
            if(allDietsJList.isSelectionEmpty() == true && addedDietsJList.isSelectionEmpty() == false){
                String returnDiet = addedDietsJList.getSelectedValue();
                
                int parenIndex = returnDiet.indexOf("(");
                
                returnDiet = returnDiet.substring(0, parenIndex).strip();
                
                if(diet.indexOf(returnDiet) == diet.lastIndexOf(returnDiet)){
                    String[] splitDiet = diet.split("((?<=[,|=]))");
                
                    diet = "";
                
                    for(int i = 0; i < splitDiet.length; i++){
                        if(splitDiet[i].contains(returnDiet) == false){
                            diet = diet + splitDiet[i];
                        }
                    }
                }
                else{
                    diet = deleteStringPortion(diet, returnDiet, "-");
                }
                
                allDietsDLM.addElement(returnDiet);
                allDietsJList.setModel(allDietsDLM);
            
                addedDietsDLM.removeElement(addedDietsJList.getSelectedValue());
                addedDietsJList.setModel(addedDietsDLM);
            
                if(diet.contains("&diet=") == false && addedDietsDLM.isEmpty() == false){
                    diet = "&diet=" + diet;
                }
            }
        }
    }//GEN-LAST:event_dietCommitActionPerformed

   /**
    * Based off of the current value of the prepTime JSpinner, the maxTime field will be changed to that value
    * @param evt - Focus on this form aspect is lost (no longer selected)
    */
    private void prepTimeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_prepTimeFocusLost
        maxTime = "&maxReadyTime=" + prepTime.getValue().toString();
    }//GEN-LAST:event_prepTimeFocusLost

    /**
     * The sortCrit field will be changed to the newly selected value within the sortCriteria JComboBox
     * @param evt - An action occurs on the currently designated form aspect
     */
    private void sortCriteriaBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortCriteriaBoxActionPerformed
        sortCrit = "&sort=" + sortCriteriaBox.getItemAt(sortCriteriaBox.getSelectedIndex());
    }//GEN-LAST:event_sortCriteriaBoxActionPerformed

    /**
     * Changes the sortDir value based on which value is selected on the check button value
     * @param evt - An action occurs on the currently designated form aspect
     */
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

    /**
     * Sets the value of the sortCrit fields based on the cortCrit check box value
     * @param evt - Action occurs on the specified Swing element
     */
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
    
    /**
     * Sets the value of the maxTime field value based on the checkbox value
     * @param evt - An action occurs on the currently designated form aspect
     */
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
     * 
     * Deletes portion of input string based off of exception and helperCharacter
     * @param inputString - string to be changed
     * @param exception - word that shows what word is being removed
     * @param helperCharacter - character that helps discern which items should not be deleted (as they have the helperCharacter in them)
     * @return newString - the new string
     */
    public String deleteStringPortion(String inputString, String exception, String helperCharacter){
        String newString = "";
        
        String[] splitString = inputString.split("((?<=[,|]))");
        
        for(int i = 0; i < splitString.length; i++){
            if(splitString[i].contains(exception) == false || splitString[i].contains(helperCharacter)){
                newString = newString + splitString[i];
            }
        }
        
        return newString;
    }
    
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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
