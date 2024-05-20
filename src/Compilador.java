
import com.formdev.flatlaf.FlatIntelliJLaf;
import compilerTools.CodeBlock;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * @author yisus
 */
public class Compilador extends javax.swing.JFrame {

    private String title;
    private Directory directorio;
    private ArrayList<Token> tokens;
    private ArrayList<ErrorLSSL> errors;
    private ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;
    private ArrayList<Production> identProd;
    private HashMap<String, String> identificadores;
    private HashMap<String, String> tipo_identificadores;
    private HashMap<String, String> dir_rutinas;
    private boolean codeHasBeenCompiled = false;

    /**
     * Creates new form Compilador
     */
    public Compilador() {
        initComponents();
        init();
    }

    private void init() {
        title = "Compiler";
        setLocationRelativeTo(null);
        setTitle(title);
        directorio = new Directory(this, jtpCode, title, ".comp");

        addWindowListener(new WindowAdapter() {// Cuando presiona la "X" de la esquina superior derecha
            @Override
            public void windowClosing(WindowEvent e) {
                directorio.Exit();
                System.exit(0);
            }
        });

        Functions.setLineNumberOnJTextComponent(jtpCode);
        timerKeyReleased = new Timer((int) (1000 * 0.3), (ActionEvent e) -> {
            timerKeyReleased.stop();
            colorAnalysis();
        });

        Functions.insertAsteriskInName(this, jtpCode, () -> {
            timerKeyReleased.restart();
        });

        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        identificadores = new HashMap<>();
        tipo_identificadores = new HashMap<>();
        dir_rutinas = new HashMap<>();

        Functions.setAutocompleterJTextComponent(new String[]{"if", "else", "while", "do", "int", "String", "float", "char", "print"}, jtpCode, () -> {
            timerKeyReleased.restart();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        buttonsFilePanel = new javax.swing.JPanel();
        btnAbrir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnGuardarC = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        panelButtonCompilerExecute = new javax.swing.JPanel();
        btnCompilar = new javax.swing.JButton();
        btnEjecutar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaOutputConsole = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTokens = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        btnAbrir.setText("Abrir");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnGuardarC.setText("Guardar como");
        btnGuardarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsFilePanelLayout = new javax.swing.GroupLayout(buttonsFilePanel);
        buttonsFilePanel.setLayout(buttonsFilePanelLayout);
        buttonsFilePanelLayout.setHorizontalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbrir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarC)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonsFilePanelLayout.setVerticalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrir)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnGuardarC))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jtpCode);

        btnCompilar.setText("Compilar");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonCompilerExecuteLayout = new javax.swing.GroupLayout(panelButtonCompilerExecute);
        panelButtonCompilerExecute.setLayout(panelButtonCompilerExecuteLayout);
        panelButtonCompilerExecuteLayout.setHorizontalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCompilar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEjecutar)
                .addContainerGap())
        );
        panelButtonCompilerExecuteLayout.setVerticalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCompilar)
                    .addComponent(btnEjecutar))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jtaOutputConsole.setEditable(false);
        jtaOutputConsole.setBackground(new java.awt.Color(255, 255, 255));
        jtaOutputConsole.setColumns(20);
        jtaOutputConsole.setRows(5);
        jScrollPane2.setViewportView(jtaOutputConsole);

        tblTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Componente léxico", "Lexema", "[Línea, Columna]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTokens.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblTokens);

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rootPanelLayout.createSequentialGroup()
                        .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        getContentPane().add(rootPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        directorio.New();
        clearFields();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        if (directorio.Open()) {
            colorAnalysis();
            clearFields();
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (directorio.Save()) {
            clearFields();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCActionPerformed
        if (directorio.SaveAs()) {
            clearFields();
        }
    }//GEN-LAST:event_btnGuardarCActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compile();
            }
        } else {
            compile();
        }
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        btnCompilar.doClick();
        CodeBlock codeBlock = Functions.splitCodeInCodeBlocks(tokens, "", " ", "\n");
        ArrayList<String> blocksOfCode = codeBlock.getBlocksOfCodeInOrderOfExec();

        executeCode(blocksOfCode);
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void executeCode(ArrayList<String> blocksOfCode) {
        // Crear un HashMap que mapea caracteres a enteros
        HashMap<String, Integer> prioridades = new HashMap<>();

        // Agregar algunos valores al mapa
        prioridades.put("=", 0);
        prioridades.put("||", 10);
        prioridades.put("&&", 20);
        prioridades.put("!", 30);
        prioridades.put("<", 40);
        prioridades.put(">", 40);
        prioridades.put("<=", 40);
        prioridades.put(">=", 40);
        prioridades.put("==", 40);
        prioridades.put("!=", 40);
        prioridades.put("+", 50);
        prioridades.put("-", 50);
        prioridades.put("*", 60);
        prioridades.put("/", 60);
        prioridades.put("%", 60);

        //Crear el arreglo donde se va a estar creando el VCI
        List<String> VCI = new ArrayList<>();

        //Crear las pilas de estatutos, de direcciones y de operadores
        Deque<String> estatutos = new LinkedList<>();
        Deque<Integer> direcciones = new LinkedList<>();
        Deque<String> operadores = new LinkedList<>();

        //Pila de tipos de datos
        Deque<String> tipo_datos = new LinkedList<>();

        //Variales temporales para print
        boolean Existeprint = false;
        boolean Existeread = false;
        boolean Existerutina = false;
        boolean Existeclase = false;

        for (int i = 0; i < blocksOfCode.size(); i++) {
            String blockOfCode = blocksOfCode.get(i);

            boolean continuar = true;
            String cadenasinEspacios = blockOfCode.replace(" ", "");

            if (cadenasinEspacios.contains("~")) {
                continuar = false;
            }

            if (identificadores.containsKey(cadenasinEspacios)) {
                VCI.add(cadenasinEspacios);

                continuar = false;
            }

            //Modifica la pila de operadores
            if (prioridades.containsKey(cadenasinEspacios)) {
                int valor = prioridades.get(cadenasinEspacios);

                while (!operadores.isEmpty() && prioridades.containsKey(operadores.peek())) {
                    int prioridadTop = prioridades.get(operadores.peek());

                    if (valor < prioridadTop) {
                        VCI.add(operadores.pop());
                    } else {
                        break;
                    }
                }

                operadores.push(cadenasinEspacios);

                continuar = false;
            }

            //Comportamiento particular del parentesis de apertura
            if (cadenasinEspacios.equals("(")) {
                operadores.push(cadenasinEspacios);
                continuar = false;
            }

            //Comportamiento particular del parentesis de cierre
            if (cadenasinEspacios.equals(")")) {
                while (!operadores.isEmpty()) {
                    if (operadores.peek().equals("(")) {
                        operadores.pop();
                        break;
                    }

                    if (operadores.size() == 1) {
                        break;
                    } else {
                        VCI.add(operadores.pop());
                    }

                }

                //Comprobar si es fin de una condicion
                if (Existeread) {
                    VCI.add("readIn");
                    Existeread = false;
                } else {
                    if (Existeprint) {
                        VCI.add("print");
                        Existeprint = false;
                    } else {
                        if (estatutos.peek() != null) {
                            if (estatutos.peek().equals("if")) {
                                direcciones.push(VCI.size());
                                VCI.add("");
                                VCI.add("if");
                            }

                            if (estatutos.peek().equals("while")) {
                                direcciones.push(VCI.size());
                                VCI.add("");
                                VCI.add("while");
                            }

                            if (estatutos.peek().equals("do")) {
                                estatutos.pop();
                                VCI.add("");
                                VCI.set(VCI.size() - 1, "" + direcciones.peek());
                                direcciones.pop();
                                VCI.add("finDo");
                            }
                        }

                    }
                }

                continuar = false;
            }

            //Comportamiento de punto y coma
            if (cadenasinEspacios.equals(";")) {
                while (!operadores.isEmpty()) {
                    VCI.add(operadores.pop());
                }

                continuar = false;
            }

            //Comportamiento de la coma
            if (cadenasinEspacios.equals(",")) {
                if (Existeread) {
                    VCI.add("readIn");
                }

                if (Existeprint) {
                    VCI.add("print");
                }

                continuar = false;
            }

            //Llaves
            if (cadenasinEspacios.equals("{")) {
                continuar = false;

            }

            if (cadenasinEspacios.equals("}")) {
                System.out.println("Encontro una llave de cierre\n");
                System.out.println("Primer estatuto en la pila: " + estatutos.peek());
                String estatutoEliminar = estatutos.pop();
                boolean esRutina = true;

                if (estatutoEliminar.equals("if")) {
                    String nextPos = blocksOfCode.get(i + 1);

                    String nexPosSinEspacios = nextPos.replace(" ", "");

                    if (nexPosSinEspacios.equals("else")) {
                        estatutos.push("else");
                        VCI.add("");
                        VCI.add("else");
                        VCI.add("");
                        VCI.set(direcciones.peek(), "" + (VCI.size() - 1));
                        VCI.remove(VCI.size() - 1);
                        direcciones.pop();
                        direcciones.push(VCI.size() - 1);
                    } else {
                        VCI.set(direcciones.peek(), "" + (VCI.size()));
                        direcciones.pop();
                    }

                    esRutina = false;
                }

                if (estatutoEliminar.equals("else")) {
                    VCI.set(direcciones.peek() - 1, "" + (VCI.size()));
                    direcciones.pop();

                    esRutina = false;
                }

                if (estatutoEliminar.equals("while")) {
                    VCI.set(direcciones.peek(), "" + (VCI.size() + 2));
                    direcciones.pop();
                    VCI.add("" + direcciones.peek());
                    direcciones.pop();
                    VCI.add("endWhile");
                    esRutina = false;
                }

                if (estatutoEliminar.equals("do")) {
                    estatutos.push("do");
                    esRutina = false;
                }

                if (Existeclase) {
                    Existeclase = false;
                }

                if (Existerutina && esRutina) {
                    VCI.add("finRutina");
                    Existerutina = false;
                    System.out.println("Se cerro una rutina");
                }

                continuar = false;
            }

            if (cadenasinEspacios.equals("else")) {
                continuar = false;
            }

            //comportamiento del print
            if (cadenasinEspacios.equals("print")) {
                Existeprint = true;
                continuar = false;
            }

            //Comportamiento del readIn
            if (cadenasinEspacios.equals("readIn")) {
                Existeread = true;
                continuar = false;
            }

            //Palabras de metodos
            if (cadenasinEspacios.equals("public")) {
                continuar = false;
            }

            if (cadenasinEspacios.equals("class")) {
                Existeclase = true;
                continuar = false;
            }

            if (cadenasinEspacios.equals("void")) {
                System.out.println("Se abrio una rutina");
                Existerutina = true;
                continuar = false;
            }

            //Tipos de datos
            if (cadenasinEspacios.equals("int")) {
                tipo_datos.push("int");
                continuar = false;
            }

            if (cadenasinEspacios.equals("String")) {
                tipo_datos.push("String");
                continuar = false;
            }

            if (cadenasinEspacios.equals("float")) {
                tipo_datos.push("float");
                continuar = false;
            }

            if (cadenasinEspacios.equals("boolean")) {
                tipo_datos.push("boolean");
                continuar = false;
            }

            if (cadenasinEspacios.equals("char")) {
                tipo_datos.push("char");
                continuar = false;
            }

            //Estatuto if
            if (cadenasinEspacios.equals("if")) {
                estatutos.push(cadenasinEspacios);
                continuar = false;
            }

            //Estatuto while
            if (cadenasinEspacios.equals("while")) {
                if (estatutos.peek() != null) {
                    if (estatutos.peek().equals("do")) {
                        continuar = false;
                    } else {
                        estatutos.push(cadenasinEspacios);
                        direcciones.push(VCI.size());
                        continuar = false;
                    }
                } else {
                    estatutos.push(cadenasinEspacios);
                    direcciones.push(VCI.size());
                    continuar = false;
                }

            }

            //Estatuto do
            if (cadenasinEspacios.equals("do")) {
                estatutos.push(cadenasinEspacios);
                direcciones.push(VCI.size());
                continuar = false;
            }

            //Comprobar si se está llamando una rutina anterior
            if (dir_rutinas.containsKey(cadenasinEspacios)) {
                VCI.add(cadenasinEspacios);
                VCI.add("ejecuta");
                continuar = false;
            }

            if (continuar) {
                if (cadenasinEspacios.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                    boolean es_variable = tipo_identificadores.containsKey(cadenasinEspacios);

                    if (!tipo_datos.isEmpty()) {
                        tipo_identificadores.put(cadenasinEspacios, tipo_datos.peek());
                        tipo_datos.pop();
                        VCI.add(cadenasinEspacios);
                    } else {
                        if (Existerutina && !es_variable) {
                            dir_rutinas.put(cadenasinEspacios, "" + (VCI.size() + 1));
                            estatutos.push(cadenasinEspacios);
                            VCI.add(cadenasinEspacios);
                            es_variable = false;
                        }

                        if (Existeclase && !es_variable) {
                            estatutos.push(cadenasinEspacios);
                            es_variable = false;
                        }
                    }

                    if (es_variable) {
                        VCI.add(cadenasinEspacios);
                    }
                } else {
                    VCI.add(cadenasinEspacios);
                }

                System.out.println("Pila de estatutos:\n" + estatutos.toString());
            }
        }

        System.out.println("Tipo identificadores:\n" + tipo_identificadores.toString());
        System.out.println("Funciones y direcciones:\n" + dir_rutinas.toString());
        System.out.println("VCI:\n" + VCI.toString());
        VCI_Exec(VCI);
    }

    private void VCI_Exec(List<String> VCI) {
        Deque<String> pilaEjecucion = new LinkedList<>();
        String consola = "";
        int dirMain = 0;
        int direccionActual = 0;
        int direccionRutina = 0;

        //Buscar el metodo main
        for (int i = 0; i < VCI.size(); i++) {
            if (VCI.get(i).equals("main")) {
                dirMain = i + 1;
            }
        }

        for (int i = dirMain; i < VCI.size(); i++) {
            switch (VCI.get(i)) {
                case "+":
                    String sum2 = pilaEjecucion.pop();
                    String sum1 = pilaEjecucion.pop();
                    int suma1;
                    int suma2;

                    if (sum1.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        suma1 = Integer.parseInt(identificadores.get(sum1));
                    } else {
                        suma1 = Integer.parseInt(sum1);
                    }

                    if (sum2.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        suma2 = Integer.parseInt(identificadores.get(sum2));
                    } else {
                        suma2 = Integer.parseInt(sum2);
                    }

                    int suma = suma1 + suma2;

                    pilaEjecucion.push("" + suma);

                    System.out.println(suma1 + "+" + suma2 + "=" + suma);
                    System.out.println(pilaEjecucion.toString());
                    break;

                case "-":
                    String res2 = pilaEjecucion.pop();
                    String res1 = pilaEjecucion.pop();
                    float resta1;
                    float resta2;

                    if (res1.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        resta1 = Integer.parseInt(identificadores.get(res1));
                    } else {
                        resta1 = Integer.parseInt(res1);
                    }

                    if (res2.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        resta2 = Integer.parseInt(identificadores.get(res2));
                    } else {
                        resta2 = Integer.parseInt(res2);
                    }

                    float resta = resta1 - resta2;

                    pilaEjecucion.push("" + resta);
                    System.out.println(resta1 + "-" + resta2 + "=" + resta);
                    System.out.println(pilaEjecucion.toString());
                    break;

                case "*":
                    String mul2 = pilaEjecucion.pop();
                    String mul1 = pilaEjecucion.pop();
                    
                    System.out.println(mul1);
                    int multi1;
                    int multi2;

                    if (mul1.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        multi1 = Integer.parseInt(identificadores.get(mul1));
                    } else {
                        multi1 = Integer.parseInt(mul1);
                    }

                    if (mul2.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        multi2 = Integer.parseInt(identificadores.get(mul2));
                    } else {
                        multi2 = Integer.parseInt(mul2);
                    }

                    int multiplicacion = multi1 * multi2;

                    pilaEjecucion.push("" + multiplicacion);
                    System.out.println(multi1 + "*" + multi2 + "=" + multiplicacion);
                    System.out.println(pilaEjecucion.toString());
                    break;

                case "/":
                    String div2 = pilaEjecucion.pop();
                    String div1 = pilaEjecucion.pop();
                    float divi1;
                    float divi2;

                    if (div1.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        divi1 = Integer.parseInt(identificadores.get(div1));
                    } else {
                        divi1 = Integer.parseInt(div1);
                    }

                    if (div2.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        divi2 = Integer.parseInt(identificadores.get(div2));
                    } else {
                        divi2 = Integer.parseInt(div2);
                    }

                    float division = divi1 / divi2;

                    pilaEjecucion.push("" + division);
                    System.out.println(div1 + "/" + div2 + "=" + division);
                    System.out.println(pilaEjecucion.toString());
                    break;

                case "%":
                    String mod2 = pilaEjecucion.pop();
                    String mod1 = pilaEjecucion.pop();
                    int modu1;
                    int modu2;

                    if (mod1.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        modu1 = Integer.parseInt(identificadores.get(mod1));
                    } else {
                        modu1 = Integer.parseInt(mod1);
                    }

                    if (mod2.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        modu2 = Integer.parseInt(identificadores.get(mod2));
                    } else {
                        modu2 = Integer.parseInt(mod2);
                    }

                    int modulo = modu1 % modu2;

                    pilaEjecucion.push("" + modulo);
                    break;

                case "=":
                    String valor = pilaEjecucion.pop();
                    String identificador = pilaEjecucion.pop();

                    identificadores.put(identificador, valor);

                    System.out.println("Valores: " + identificadores.toString());
                    break;

                case ">":
                    String may2 = pilaEjecucion.pop();
                    String may1 = pilaEjecucion.pop();
                    float mayor1;
                    float mayor2;

                    if (may1.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        mayor1 = Integer.parseInt(identificadores.get(may1));
                    } else {
                        mayor1 = Integer.parseInt(may1);
                    }

                    if (may2.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        mayor2 = Integer.parseInt(identificadores.get(may1));
                    } else {
                        mayor2 = Integer.parseInt(may2);
                    }

                    if (mayor1 > mayor2) {
                        pilaEjecucion.push("" + 1);
                    } else {
                        pilaEjecucion.push("" + 0);
                    }
                    break;

                case "<":
                    String men2 = pilaEjecucion.pop();
                    String men1 = pilaEjecucion.pop();
                    int menor1;
                    int menor2;

                    if (men1.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        menor1 = Integer.parseInt(identificadores.get(men1));
                    } else {
                        menor1 = Integer.parseInt(men1);
                    }

                    if (men2.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        menor2 = Integer.parseInt(identificadores.get(men1));
                    } else {
                        menor2 = Integer.parseInt(men2);
                    }

                    if (menor1 < menor2) {
                        pilaEjecucion.push("" + 1);
                    } else {
                        pilaEjecucion.push("" + 0);
                    }
                    break;

                case ">=":
                    String mayi2 = pilaEjecucion.pop();
                    String mayi1 = pilaEjecucion.pop();
                    float mayorigual1;
                    float mayorigual2;

                    if (mayi1.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        mayorigual1 = Integer.parseInt(identificadores.get(mayi1));
                    } else {
                        mayorigual1 = Integer.parseInt(mayi1);
                    }

                    if (mayi2.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        mayorigual2 = Integer.parseInt(identificadores.get(mayi1));
                    } else {
                        mayorigual2 = Integer.parseInt(mayi2);
                    }

                    if (mayorigual1 >= mayorigual2) {
                        pilaEjecucion.push("" + 1);
                    } else {
                        pilaEjecucion.push("" + 0);
                    }
                    break;

                case "<=":
                    String meni2 = pilaEjecucion.pop();
                    String meni1 = pilaEjecucion.pop();
                    float menorigual1;
                    float menorigual2;

                    if (meni1.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        menorigual1 = Integer.parseInt(identificadores.get(meni1));
                    } else {
                        menorigual1 = Integer.parseInt(meni1);
                    }

                    if (meni2.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        menorigual2 = Integer.parseInt(identificadores.get(meni1));
                    } else {
                        menorigual2 = Integer.parseInt(meni2);
                    }

                    if (menorigual1 <= menorigual2) {
                        pilaEjecucion.push("" + 1);
                    } else {
                        pilaEjecucion.push("" + 0);
                    }
                    break;

                case "!":
                    //Por el momento, nada
                    break;

                case "&&":
                    int and2 = Integer.parseInt(pilaEjecucion.pop());
                    int and1 = Integer.parseInt(pilaEjecucion.pop());

                    if (and1 == 1 && and2 == 1) {
                        pilaEjecucion.push("" + 1);
                    } else {
                        pilaEjecucion.push("" + 0);
                    }
                    break;

                case "||":
                    int or2 = Integer.parseInt(pilaEjecucion.pop());
                    int or1 = Integer.parseInt(pilaEjecucion.pop());

                    if (or1 == 1 || or2 == 0) {
                        pilaEjecucion.push("" + 1);
                    } else {
                        pilaEjecucion.push("" + 0);
                    }
                    break;

                case "!=":
                    String dif2 = pilaEjecucion.pop();
                    String dif1 = pilaEjecucion.pop();
                    float diferente1;
                    float diferente2;

                    if (dif1.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        diferente1 = Integer.parseInt(identificadores.get(dif1));
                    } else {
                        diferente1 = Integer.parseInt(dif1);
                    }

                    if (dif2.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        diferente2 = Integer.parseInt(identificadores.get(dif1));
                    } else {
                        diferente2 = Integer.parseInt(dif2);
                    }

                    if (diferente1 != diferente2) {
                        pilaEjecucion.push("" + 1);
                    } else {
                        pilaEjecucion.push("" + 0);
                    }
                    break;

                case "==":
                    String ig2 = pilaEjecucion.pop();
                    String ig1 = pilaEjecucion.pop();
                    float igual1;
                    float igual2;

                    if (ig1.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        igual1 = Integer.parseInt(identificadores.get(ig1));
                    } else {
                        igual1 = Integer.parseInt(ig1);
                    }

                    if (ig2.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        igual2 = Integer.parseInt(identificadores.get(ig1));
                    } else {
                        igual2 = Integer.parseInt(ig2);
                    }

                    if (igual1 == igual2) {
                        pilaEjecucion.push("" + 1);
                    } else {
                        pilaEjecucion.push("" + 0);
                    }
                    break;

                case "print":
                    String valorParaImprimir = pilaEjecucion.pop();

                    if (valorParaImprimir.matches("^[a-zA-Z]+[a-zA-Z0-9]*$")) {
                        consola += "\n" + identificadores.get(valorParaImprimir);
                    } else {
                        consola += "\n" + valorParaImprimir;
                    }

                    System.out.println(identificadores.toString());

                    jtaOutputConsole.setText(consola);
                    break;

                case "readIn":
                    String valorParaLeer = pilaEjecucion.pop();

                    String tipoDatoParaLeer = tipo_identificadores.get(valorParaLeer);

                    switch (tipoDatoParaLeer) {
                        case "int":
                            int valorInt = Integer.parseInt(JOptionPane.showInputDialog(null, "Inserta valor entero para " + valorParaLeer));
                            identificadores.put(valorParaLeer, ("" + valorInt));
                            break;

                        case "String":
                            String valorString = JOptionPane.showInputDialog("Inserta valor de String para " + valorParaLeer);
                            identificadores.put(valorParaLeer, valorString);
                            break;

                        case "float":
                            float valorFloat = Integer.parseInt(JOptionPane.showInputDialog("Inserta valor float para " + valorParaLeer));
                            identificadores.put(valorParaLeer, "" + valorFloat);
                            break;

                        case "boolean":
                            boolean valorBool = Boolean.parseBoolean(JOptionPane.showInputDialog("Inserta valor boolean para " + valorParaLeer));
                            identificadores.put(valorParaLeer, "" + valorBool);
                            break;

                        case "char":
                            String valorChar = JOptionPane.showInputDialog("Inserta valor char para " + valorParaLeer);
                            identificadores.put(valorParaLeer, "" + valorChar);
                            break;
                    }
                    break;

                case "if":
                    int direccionIf = Integer.parseInt(pilaEjecucion.pop());
                    int condicionIf = Integer.parseInt(pilaEjecucion.pop());

                    System.out.println(direccionIf);

                    if (condicionIf == 0) {
                        i = direccionIf - 1;
                    }
                    break;

                case "else":
                    int direccionElse = Integer.parseInt(pilaEjecucion.pop());

                    System.out.println(direccionElse);

                    i = direccionElse - 1;
                    break;

                case "while":
                    int direccionWhile = Integer.parseInt(pilaEjecucion.pop());
                    int condicionWhile = Integer.parseInt(pilaEjecucion.pop());

                    System.out.println(direccionWhile);

                    if (condicionWhile == 0) {
                        i = direccionWhile - 1;
                    }
                    break;

                case "endWhile":
                    int direccionFinWhile = Integer.parseInt(pilaEjecucion.pop());

                    System.out.println(direccionFinWhile);

                    i = direccionFinWhile - 1;
                    break;

                case "finDo":
                    int direccionDo = Integer.parseInt(pilaEjecucion.pop());
                    int condicionDo = Integer.parseInt(pilaEjecucion.pop());

                    System.out.println("Direccion do: " + direccionDo);

                    if (condicionDo == 1) {
                        i = direccionDo - 1;
                    }
                    break;

                case "ejecuta":
                    String nombreRutina = pilaEjecucion.pop();
                    System.out.println("Encontro una rutina que ejecutar: " + nombreRutina);
                    direccionActual = i;

                    direccionRutina = Integer.parseInt(dir_rutinas.get(nombreRutina));

                    i = direccionRutina - 1;
                    break;

                case "finRutina":
                    direccionActual += 1;
                    i = direccionActual - 1;
                    break;

                default:
                    pilaEjecucion.push(VCI.get(i));
                    break;
            }

            System.out.println(pilaEjecucion.toString());
        }

    }

    private void compile() {
        clearFields();
        lexicalAnalysis();
        fillTableTokens();
        syntacticAnalysis();
        semanticAnalysis();
        printConsole();
        codeHasBeenCompiled = true;
    }

    private void lexicalAnalysis() {
        // Extraer tokens
        Lexer lexer;
        try {
            File codigo = new File("code.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexer = new Lexer(entrada);

            while (true) {
                Token token = lexer.yylex();
                if (token == null) {
                    break;
                }
                tokens.add(token);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
    }

    private void syntacticAnalysis() {
        Grammar gramatica = new Grammar(tokens, errors);

        /*Eliminación de errores*/
        gramatica.delete(new String[]{"ERROR", "ERROR_1", "ERROR_2"}, 1);

        /*Agrupación de valores básicos*/
        gramatica.group("CTE", "ENTERO | TEXTO | CHARACTER | REAL | BOLEANO");

        gramatica.group("TIPO_DATO", "INT | STRING | FLOAT | BOOLEAN | CHAR", true);

        gramatica.group("OP_ARIT", "SUMA | RESTA | MULTIPLICACION | DIVISION | MODULO | MULTIPLICACION", true);

        gramatica.group("OP_REL", "IGUAL_IGUAL | MAYOR_IGUAL | MENOR_IGUAL | MAYOR | MENOR | DIFERENTE", true);

        gramatica.group("OP_LOG", "AND | OR", true);

        /*Estatuto Read*/
        gramatica.group("EST_READ", "READIN PARENTESIS_A IDENTIFICADOR PARENTESIS_C PUNTO_COMA | "
                + "READIN PARENTESIS_A IDENTIFICADOR (COMA IDENTIFICADOR)+ PARENTESIS_C PUNTO_COMA");

        /*Manejo de errores*/
 /*gramatica.group("EST_READ", "READIN IDENTIFICADOR PARENTESIS_C PUNTO_COMA | "
                + "READIN IDENTIFICADOR (COMA IDENTIFICADOR)+ PARENTESIS_C PUNTO_COMA | "
                + "READIN PARENTESIS_A IDENTIFICADOR PUNTO_COMA | "
                + "READIN PARENTESIS_A IDENTIFICADOR (COMA)+ PUNTO_COMA | "
                + "READIN PARENTESIS_A(COMA)+  IDENTIFICADOR PUNTO_COMA | "
                + "READIN PARENTESIS_A IDENTIFICADOR (COMA IDENTIFICADOR)+ PUNTO_COMA", 3, "Error sintáctico {}: Falta parentesis de apertura o cierre en la expresión [#, %]");

        gramatica.group("EST_READ", "READIN PARENTESIS_A IDENTIFICADOR PARENTESIS_C | "
                + "READIN PARENTESIS_A IDENTIFICADOR (COMA IDENTIFICADOR)+ PARENTESIS_C", 4, "Error sintáctico {}: Falta el punto y coma al final de la expresión [#, %]");

        gramatica.group("EST_READ", "READIN PARENTESIS_A PARENTESIS_C PUNTO_COMA", 5, "Error sintáctico {}: Nada que leer en la expresión [#, %]");
         */
        gramatica.finalLineColumn();

        /*Estatuto Expresiones Aritmeticas*/
        gramatica.group("EXP_ARIT", "CTE (OP_ARIT (CTE | IDENTIFICADOR))+|"
                + "(PARENTESIS_A)+ CTE (OP_ARIT (CTE | IDENTIFICADOR))+ (PARENTESIS_C)+");

        /*Manejo de errores*/
 /*gramatica.group("EXP_ARIT", "CTE (OP_ARIT (CTE | IDENTIFICADOR))+|"
                + "CTE (OP_ARIT (CTE | IDENTIFICADOR))+ (PARENTESIS_C)+ | "
                + "(PARENTESIS_A)+ CTE (OP_ARIT (CTE | IDENTIFICADOR))+", 6, "Error sintáctico {}: Falta parentesis de apertura o cierre en la expresión [#, %]");
         */
 /*gramatica.group("EXP_ARIT", "(OP_ARIT (CTE | IDENTIFICADOR))+ | "
                + "CTE (OP_ARIT (CTE | IDENTIFICADOR))+ (OP_ARIT)+ | "
                + "(PARENTESIS_A)+ (OP_ARIT (CTE | IDENTIFICADOR))+ (PARENTESIS_C)+ | "
                + "(PARENTESIS_A)+ CTE (OP_ARIT (CTE | IDENTIFICADOR))+ (OP_ARIT)+ (PARENTESIS_C)+", 7, "Error sintáctico {}: Expresión incompleta [#, %]");*/
        gramatica.finalLineColumn();

        /*Estatuto de condición*/
        gramatica.group("CONDICION", "(IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO) | "
                + "(IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO) (OP_LOG (IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO))+  | "
                + "NOT (IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO) | "
                + "NOT (IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO) (OP_LOG (IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO))+");

        /*Manejo de errores*/
 /*gramatica.group("CONDICION", "(IDENTIFICADOR | EXP_ARIT) OP_REL | "
                + "OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO) | "
                + "(IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO) (OP_LOG)+ | "
                + "(OP_LOG (IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO))+ | "
                + "NOT (IDENTIFICADOR | EXP_ARIT) OP_REL | "
                + "NOT OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO) | "
                + "NOT (IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO) (OP_LOG)+ | "
                + "NOT (OP_LOG (IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO))+", 8, "Error sintáctico {}: Condición incompleta [#, %]");
         */
        gramatica.finalLineColumn();

        /*Estatuto declarativo*/
        gramatica.group("EST_DECLAR", "TIPO_DATO IDENTIFICADOR IGUAL EXP_ARIT PUNTO_COMA | "
                + "TIPO_DATO IDENTIFICADOR PUNTO_COMA | "
                + "TIPO_DATO IDENTIFICADOR IGUAL (CTE | IDENTIFICADOR) PUNTO_COMA", true, identProd);

        /*Manejo de errores*/
 /*gramatica.group("EST_DECLAR", "TIPO_DATO IGUAL EXP_ARIT PUNTO_COMA | "
                + "TIPO_DATO PUNTO_COMA | "
                + "TIPO_DATO IGUAL (CTE | IDENTIFICADOR) PUNTO_COMA", 2, "Error sintáctico {}: Falta el identificador en la variable [#, %]");

        gramatica.finalLineColumn();

        gramatica.group("EST_DECLAR", "TIPO_DATO IDENTIFICADOR IGUAL EXP_ARIT | "
                + "TIPO_DATO IDENTIFICADOR | "
                + "TIPO_DATO IDENTIFICADOR IGUAL (CTE | IDENTIFICADOR)", 3, "Error sintáctico {}: Falta el punto y coma al final de la expresión [#, %]");

        gramatica.finalLineColumn();
         */
 /*Estatuto asignación*/
        gramatica.group("EST_ASIG", "IDENTIFICADOR IGUAL EXP_ARIT PUNTO_COMA | "
                + "IDENTIFICADOR IGUAL (CTE | IDENTIFICADOR) PUNTO_COMA");

        /*Manejo de errores*/
 /*gramatica.group("EST_ASIG", "IGUAL EXP_ARIT PUNTO_COMA | "
                + "IGUAL (CTE | IDENTIFICADOR) PUNTO_COMA", 9, "Error sintáctico {}: Falta el identificador en la variable [#, %]");

        gramatica.group("EST_ASIG", "IDENTIFICADOR IGUAL EXP_ARIT | "
                + "IDENTIFICADOR IGUAL (CTE | IDENTIFICADOR)", 10, "Error sintáctico {}: Falta el punto y coma al final de la expresión [#, %]");

        gramatica.group("EST_ASIG", "IDENTIFICADOR IGUAL PUNTO_COMA | "
                + "IDENTIFICADOR IGUAL PUNTO_COMA", 11, "Error sintáctico {}: Expresión incompleta [#, %]");
         */
 /*Estatuto if*/
        gramatica.group("EST_IF", "IF PARENTESIS_A CONDICION PARENTESIS_C LLAVE_A (S)+ LLAVE_C PUNTO_COMA");

        /*Manejo de errores*/
 /*gramatica.group("EST_IF", "IF CONDICION PARENTESIS_C LLAVE_A (S)+ LLAVE_C PUNTO_COMA | "
                + "IF PARENTESIS_A CONDICION LLAVE_A (S)+ LLAVE_C PUNTO_COMA | "
                + "IF PARENTESIS_A PARENTESIS_C LLAVE_A (S)+ LLAVE_C PUNTO_COMA | "
                + "IF PARENTESIS_A CONDICION PARENTESIS_C (S)+ LLAVE_C PUNTO_COMA | "
                + "IF PARENTESIS_A CONDICION PARENTESIS_C LLAVE_A (S)+ PUNTO_COMA | "
                + "IF PARENTESIS_A CONDICION PARENTESIS_C LLAVE_A LLAVE_C PUNTO_COMA | "
                + "IF PARENTESIS_A CONDICION PARENTESIS_C LLAVE_A (S)+ LLAVE_C", 12, "Error sintactico {}: Expresion if incompleta [#, %]");
         */
 /*Estatuto while*/
        gramatica.group("EST_WHILE", "WHILE PARENTESIS_A CONDICION PARENTESIS_C LLAVE_A (S)+ LLAVE_C PUNTO_COMA");

        /*Manejo de errores*/
 /*gramatica.group("EST_WHILE", "WHILE CONDICION PARENTESIS_C LLAVE_A (S)+ LLAVE_C PUNTO_COMA | "
                + "WHILE PARENTESIS_A PARENTESIS_C LLAVE_A (S)+ LLAVE_C PUNTO_COMA | "
                + "WHILE PARENTESIS_A CONDICION LLAVE_A (S)+ LLAVE_C PUNTO_COMA | "
                + "WHILE PARENTESIS_A CONDICION PARENTESIS_C (S)+ LLAVE_C PUNTO_COMA | "
                + "WHILE PARENTESIS_A CONDICION PARENTESIS_C LLAVE_A LLAVE_C PUNTO_COMA | "
                + "WHILE PARENTESIS_A CONDICION PARENTESIS_C LLAVE_A (S) PUNTO_COMA | "
                + "WHILE PARENTESIS_A CONDICION PARENTESIS_C LLAVE_A (S)+ LLAVE_C", 12, "Error sintáctico {}: Estatuto while incompleto [#, %]");
         */
 /*Estatuto do while*/
        gramatica.group("EST_DOWHILE", "DO LLAVE_A (S)+ LLAVE_C WHILE PARENTESIS_A CONDICION PARENTESIS_C PUNTO_COMA");

        /*Manejo de errores*/
 /*gramatica.group("EST_DOWHILE", "DO (S)+ LLAVE_C WHILE PARENTESIS_A CONDICION PARENTESIS_C PUNTO_COMA | "
                + "DO LLAVE_A (S)+ LLAVE_C PUNTO_COMA | "
                + "DO LLAVE_A LLAVE_C WHILE PARENTESIS_A CONDICION PARENTESIS_C PUNTO_COMA | "
                + "DO LLAVE_A (S)+ WHILE PARENTESIS_A CONDICION PARENTESIS_C PUNTO_COMA | "
                + "DO LLAVE_A (S)+ LLAVE_C PARENTESIS_A CONDICION PARENTESIS_C PUNTO_COMA | "
                + "DO LLAVE_A (S)+ LLAVE_C WHILE CONDICION PARENTESIS_C PUNTO_COMA | "
                + "DO LLAVE_A (S)+ LLAVE_C WHILE PARENTESIS_A PARENTESIS_C PUNTO_COMA | "
                + "DO LLAVE_A (S)+ LLAVE_C WHILE PARENTESIS_A CONDICION PUNTO_COMA | "
                + "DO LLAVE_A (S)+ LLAVE_C WHILE PARENTESIS_A CONDICION PARENTESIS_C", 13, "Error sintáctico {}: Estatuto do while incompleto [#, %]");
         */
 /*Estatuto S*/
        gramatica.group("S", "EST_READ | EST_DECLAR | EST_ASIG | EST_IF | EST_WHILE | EST_DOWHILE");

        /*Estatuto Metodos*/
        gramatica.group("FUNCTIONS", "PUBLIC STATIC VOID IDENTIFICADOR PARENTESIS_A PARENTESIS_C LLAVE_A (S)+ LLAVE_C | "
                + "PRIVATE STATIC VOID IDENTIFICADOR PARENTESIS_A PARENTESIS_C LLAVE_A (S)+ LLAVE_C | "
                + "PUBLIC STATIC TIPO_DATO IDENTIFICADOR PARENTESIS_A PARENTESIS_C LLAVE_A (S)+ RETURN EXP_ARIT LLAVE_C | "
                + "PRIVATE STATIC TIPO_DATO IDENTIFICADOR PARENTESIS_A PARENTESIS_C LLAVE_A (S)+ RETURN EXP_ARIT LLAVE_C | "
                + "PUBLIC VOID IDENTIFICADOR PARENTESIS_A PARENTESIS_C LLAVE_A (S)+ LLAVE_C | "
                + "PRIVATE VOID IDENTIFICADOR PARENTESIS_A PARENTESIS_C LLAVE_A (S)+ LLAVE_C | "
                + "PUBLIC VOID IDENTIFICADOR PARENTESIS_A PARENTESIS_C LLAVE_A (S)+ RETURN EXP_ARIT LLAVE_C | "
                + "PRIVATE VOID IDENTIFICADOR PARENTESIS_A PARENTESIS_C LLAVE_A (S)+ RETURN EXP_ARIT LLAVE_C");

        /*Estatuto programa*/
        gramatica.group("PROGRAM", "PUBLIC CLASS IDENTIFICADOR LLAVE_A FUNCTIONS LLAVE_C");

        /* Mostrar gramáticas */
        gramatica.show();
    }

    private void semanticAnalysis() {
        /*HashMap<String, String> identDataType = new HashMap<>();
        identDataType.put("int", "ENTERO");
        identDataType.put("String", "TEXTO");
        identDataType.put("float", "REAL");
        identDataType.put("boolean", "BOLEANO");
        identDataType.put("char", "CHARACTER");*/
    }

    private void colorAnalysis() {
        /* Limpiar el arreglo de colores */
        textsColor.clear();
        /* Extraer rangos de colores */
        LexerColor lexerColor;
        try {
            File codigo = new File("color.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexerColor = new LexerColor(entrada);
            while (true) {
                TextColor textColor = lexerColor.yylex();
                if (textColor == null) {
                    break;
                }
                textsColor.add(textColor);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
        Functions.colorTextPane(textsColor, jtpCode, new Color(40, 40, 40));
    }

    private void fillTableTokens() {
        tokens.forEach(token -> {
            Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(), "[" + token.getLine() + ", " + token.getColumn() + "]"};
            Functions.addRowDataInTable(tblTokens, data);
        });
    }

    private void printConsole() {
        /*int sizeErrors = errors.size();

        if (sizeErrors > 0) {
            Functions.sortErrorsByLineAndColumn(errors);
            String strErrors = "\n";
            for (ErrorLSSL error : errors) {
                String strError = String.valueOf(error);
                strErrors += strError + "\n";
            }
            jtaOutputConsole.setText("Compilación terminada...\n" + strErrors + "\nLa compilación terminó con errores...");
        } else {
            jtaOutputConsole.setText("Compilación terminada...");
        }
        jtaOutputConsole.setCaretPosition(0);*/
    }

    private void clearFields() {
        Functions.clearDataInTable(tblTokens);
        jtaOutputConsole.setText("");
        tokens.clear();
        errors.clear();
        identProd.clear();
        identificadores.clear();
        codeHasBeenCompiled = false;
    }

    /**
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
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                System.out.println("LookAndFeel no soportado: " + ex);
            }
            new Compilador().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarC;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel buttonsFilePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jtaOutputConsole;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JPanel panelButtonCompilerExecute;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JTable tblTokens;
    // End of variables declaration//GEN-END:variables
}
