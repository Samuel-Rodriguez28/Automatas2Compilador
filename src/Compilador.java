
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
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.Timer;

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
        if (codeHasBeenCompiled) {
            if (!errors.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se puede ejecutar el código ya que se encontró uno o más errores",
                        "Error en la compilación", JOptionPane.ERROR_MESSAGE);
            } else {
                CodeBlock codeBlock = Functions.splitCodeInCodeBlocks(tokens, "{", "}", ";");
                System.out.println(codeBlock);
                ArrayList<String> blocksOfCode = codeBlock.getBlocksOfCodeInOrderOfExec();
                System.out.println(blocksOfCode);

            }
        }
    }//GEN-LAST:event_btnEjecutarActionPerformed

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
        gramatica.group("CTE", "ENTERO | TEXTO | CHAR | REAL | BOLEANO");
        
        gramatica.group("TIPO_DATO", "INT | STRING | FLOAT | BOOLEAN | CHAR", true);

        gramatica.group("OP_ARIT", "SUMA | RESTA | MULTIPLICACION | DIVISION | MODULO | MULTIPLICACION", true);
        
        gramatica.group("OP_REL", "IGUAL_IGUAL | MAYOR_IGUAL | MENOR_IGUAL | MAYOR | MENOR | DIFERENTE", true);

        gramatica.group("OP_LOG", "AND | OR", true);
        
        /*Estatuto Read*/
        gramatica.group("EST_READ", "READIN PARENTESIS_A IDENTIFICADOR PARENTESIS_C PUNTO_COMA | "
                                        + "READIN PARENTESIS_A IDENTIFICADOR (COMA IDENTIFICADOR)+ PARENTESIS_C PUNTO_COMA");
        
        /*Manejo de errores*/
        gramatica.group("EST_READ", "READIN IDENTIFICADOR PARENTESIS_C PUNTO_COMA | "
                                        + "READIN IDENTIFICADOR (COMA IDENTIFICADOR)+ PARENTESIS_C PUNTO_COMA | "
                                        + "READIN PARENTESIS_A IDENTIFICADOR PUNTO_COMA | "
                                        + "READIN PARENTESIS_A IDENTIFICADOR (COMA)+ PUNTO_COMA | "
                                        + "READIN PARENTESIS_A(COMA)+  IDENTIFICADOR PUNTO_COMA | "
                                        + "READIN PARENTESIS_A IDENTIFICADOR (COMA IDENTIFICADOR)+ PUNTO_COMA", 3, "Error sintáctico: Falta parentesis de apertura o cierre en la expresión [#, %]");
        
        gramatica.group("EST_READ", "READIN PARENTESIS_A IDENTIFICADOR PARENTESIS_C | "
                                        + "READIN PARENTESIS_A IDENTIFICADOR (COMA IDENTIFICADOR)+ PARENTESIS_C", 4, "Error sintáctico: Falta el punto y coma al final de la expresión [#, %]");
        
        gramatica.group("EST_READ", "READIN PARENTESIS_A PARENTESIS_C PUNTO_COMA", 5, "Error sintáctico: Nada que leer en la expresión [#, %]");
        
        gramatica.finalLineColumn();
        
        /*Estatuto Expresiones Aritmeticas*/
        gramatica.group("EXP_ARIT", "CTE (OP_ARIT (CTE | IDENTIFICADOR))+|"
                                        + "(PARENTESIS_A)+ CTE (OP_ARIT (CTE | IDENTIFICADOR))+ (PARENTESIS_C)+");
        
        /*Manejo de errores*/
        gramatica.group("EXP_ARIT", "CTE (OP_ARIT (CTE | IDENTIFICADOR))+|"
                                        + "CTE (OP_ARIT (CTE | IDENTIFICADOR))+ (PARENTESIS_C)+ | "
                                        + "(PARENTESIS_A)+ CTE (OP_ARIT (CTE | IDENTIFICADOR))+", 6, "Error sintáctico: Falta parentesis de apertura o cierre en la expresión [#, %]");
        
        gramatica.group("EXP_ARIT", "(OP_ARIT (CTE | IDENTIFICADOR))+ | "
                                        + "CTE (OP_ARIT (CTE | IDENTIFICADOR))+ (OP_ARIT)+ | "
                                        + "(PARENTESIS_A)+ (OP_ARIT (CTE | IDENTIFICADOR))+ (PARENTESIS_C)+ | "
                                        + "(PARENTESIS_A)+ CTE (OP_ARIT (CTE | IDENTIFICADOR))+ (OP_ARIT)+ (PARENTESIS_C)+", 7, "Error sintáctico: Expresión incompleta [#, %]");
        
        gramatica.finalLineColumn();
        
        /*Estatuto de condición*/
        gramatica.group("CONDICION", "(IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO) | "
                                         + "(IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO) (OP_LOG (IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO))+  | "
                                         + "NOT (IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO) | "
                                         + "NOT (IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO) (OP_LOG (IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO))+");
        
        /*Manejo de errores*/
        gramatica.group("CONDICION", "(IDENTIFICADOR | EXP_ARIT) OP_REL | "
                                         + "OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO) | "
                                         + "(IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO) (OP_LOG)+ | "
                                         + "(OP_LOG (IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO))+ | "
                                         + "NOT (IDENTIFICADOR | EXP_ARIT) OP_REL | "
                                         + "NOT OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO) | "
                                         + "NOT (IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO) (OP_LOG)+ | "
                                         + "NOT (OP_LOG (IDENTIFICADOR | EXP_ARIT) OP_REL (IDENTIFICADOR | EXP_ARIT | BOLEANO))+", 8, "Error sintáctico: Condición incompleta [#, %]");
        
        gramatica.finalLineColumn();
        
        /*Estatuto declarativo*/
        gramatica.group("EST_DECLAR", "TIPO_DATO IDENTIFICADOR IGUAL EXP_ARIT PUNTO_COMA | "
                                          + "TIPO_DATO IDENTIFICADOR PUNTO_COMA | "
                                          + "TIPO_DATO IDENTIFICADOR IGUAL (CTE | IDENTIFICADOR) PUNTO_COMA");
        
        /*Manejo de errores*/
        gramatica.group("EST_DECLAR", "TIPO_DATO IGUAL EXP_ARIT PUNTO_COMA | "
                                          + "TIPO_DATO PUNTO_COMA | "
                                          + "TIPO_DATO IGUAL (CTE | IDENTIFICADOR) PUNTO_COMA", 2, "Error sintáctico: Falta el identificador en la variable [#, %]");
        
        gramatica.finalLineColumn();
        
        gramatica.group("EST_DECLAR", "TIPO_DATO IDENTIFICADOR IGUAL EXP_ARIT | "
                                          + "TIPO_DATO IDENTIFICADOR | "
                                          + "TIPO_DATO IDENTIFICADOR IGUAL (CTE | IDENTIFICADOR)", 3, "Error sintáctico: Falta el punto y coma al final de la expresión [#, %]");
        
        gramatica.finalLineColumn();
        
        /*Estatuto asignación*/
        gramatica.group("EST_ASIG", "IDENTIFICADOR IGUAL EXP_ARIT PUNTO_COMA | "
                                        + "IDENTIFICADOR IGUAL (CTE | IDENTIFICADOR) PUNTO_COMA");
        
        /*Manejo de errores*/
        gramatica.group("EST_ASIG", "IGUAL EXP_ARIT PUNTO_COMA | "
                                        + "IGUAL (CTE | IDENTIFICADOR) PUNTO_COMA", 9, "Error sintáctico: Falta el identificador en la variable [#, %]");
        
        gramatica.group("EST_ASIG", "IDENTIFICADOR IGUAL EXP_ARIT | "
                                        + "IDENTIFICADOR IGUAL (CTE | IDENTIFICADOR)", 10, "Error sintáctico: Falta el punto y coma al final de la expresión [#, %]");
        
        gramatica.group("EST_ASIG", "IDENTIFICADOR IGUAL PUNTO_COMA | "
                                        + "IDENTIFICADOR IGUAL PUNTO_COMA", 11, "Error sintáctico: Expresión incompleta [#, %]");
        
        /*Estatuto if*/
        gramatica.group("EST_IF", "IF PARENTESIS_A CONDICION PARENTESIS_C LLAVE_A (S)+ LLAVE_C PUNTO_COMA");
        
        /*Manejo de errores*/
        gramatica.group("EST_IF", "IF CONDICION PARENTESIS_C LLAVE_A (S)+ LLAVE_C PUNTO_COMA | "
                                      + "IF PARENTESIS_A CONDICION LLAVE_A (S)+ LLAVE_C PUNTO_COMA | "
                                      + "IF PARENTESIS_A PARENTESIS_C LLAVE_A (S)+ LLAVE_C PUNTO_COMA | "
                                      + "IF PARENTESIS_A CONDICION PARENTESIS_C (S)+ LLAVE_C PUNTO_COMA | "
                                      + "IF PARENTESIS_A CONDICION PARENTESIS_C LLAVE_A (S)+ PUNTO_COMA | "
                                      + "IF PARENTESIS_A CONDICION PARENTESIS_C LLAVE_A LLAVE_C PUNTO_COMA | "
                                      + "IF PARENTESIS_A CONDICION PARENTESIS_C LLAVE_A (S)+ LLAVE_C", 12, "Error sintactico: Expresion if incompleta [#, %]");
        
        /*Estatuto while*/
        gramatica.group("EST_WHILE", "WHILE PARENTESIS_A CONDICION PARENTESIS_C LLAVE_A (S)+ LLAVE_C PUNTO_COMA");
        
        /*Manejo de errores*/
        gramatica.group("EST_WHILE", "WHILE CONDICION PARENTESIS_C LLAVE_A (S)+ LLAVE_C PUNTO_COMA | "
                                         + "WHILE PARENTESIS_A PARENTESIS_C LLAVE_A (S)+ LLAVE_C PUNTO_COMA | "
                                         + "WHILE PARENTESIS_A CONDICION LLAVE_A (S)+ LLAVE_C PUNTO_COMA | "
                                         + "WHILE PARENTESIS_A CONDICION PARENTESIS_C (S)+ LLAVE_C PUNTO_COMA | "
                                         + "WHILE PARENTESIS_A CONDICION PARENTESIS_C LLAVE_A LLAVE_C PUNTO_COMA | "
                                         + "WHILE PARENTESIS_A CONDICION PARENTESIS_C LLAVE_A (S) PUNTO_COMA | "
                                         + "WHILE PARENTESIS_A CONDICION PARENTESIS_C LLAVE_A (S)+ LLAVE_C", 12, "Error sintáctico: Estatuto while incompleto [#, %]");
        
        /*Estatuto do while*/
        gramatica.group("EST_DOWHILE", "DO LLAVE_A (S)+ LLAVE_C WHILE PARENTESIS_A CONDICION PARENTESIS_C PUNTO_COMA");
        
        /*Manejo de errores*/
        gramatica.group("EST_DOWHILE", "DO (S)+ LLAVE_C WHILE PARENTESIS_A CONDICION PARENTESIS_C PUNTO_COMA | "
                                           + "DO LLAVE_A LLAVE_C WHILE PARENTESIS_A CONDICION PARENTESIS_C PUNTO_COMA | "
                                           + "DO LLAVE_A (S)+ WHILE PARENTESIS_A CONDICION PARENTESIS_C PUNTO_COMA | "
                                           + "DO LLAVE_A (S)+ LLAVE_C PARENTESIS_A CONDICION PARENTESIS_C PUNTO_COMA | "
                                           + "DO LLAVE_A (S)+ LLAVE_C WHILE CONDICION PARENTESIS_C PUNTO_COMA | "
                                           + "DO LLAVE_A (S)+ LLAVE_C WHILE PARENTESIS_A PARENTESIS_C PUNTO_COMA | "
                                           + "DO LLAVE_A (S)+ LLAVE_C WHILE PARENTESIS_A CONDICION PUNTO_COMA | "
                                           + "DO LLAVE_A (S)+ LLAVE_C WHILE PARENTESIS_A CONDICION PARENTESIS_C");
        
        /*Estatuto S*/
        gramatica.group("S", "EST_READ | EST_DECLAR | EST_ASIG | EST_IF | EST_WHILE | EST_DOWHILE");
        
        /* Mostrar gramáticas */
        gramatica.show();
    }

    private void semanticAnalysis() {
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
        int sizeErrors = errors.size();
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
        jtaOutputConsole.setCaretPosition(0);
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
