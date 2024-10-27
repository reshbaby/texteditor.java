

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;
	import java.io.*;

	public class textEditor extends JFrame implements ActionListener {

		
		
		    // Text area for editing
		    JTextArea textArea;
		    // Frame for the text editor
		    JFrame frame;
		    // Default font settings
		    String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		    int defaultFontSize = 16;
		    String defaultFontFamily = "Serif";

		    // Constructor to set up the text editor
		    public textEditor() {
		        // Create the frame
		        frame = new JFrame("Enhanced Text Editor");

		        // Set default size
		        frame.setSize(800, 600);

		        // Create a menu bar
		        JMenuBar menuBar = new JMenuBar();

		        // Create file menu
		        JMenu fileMenu = new JMenu("File");

		        // Create menu items for File menu
		        JMenuItem newFile = new JMenuItem("New");
		        JMenuItem openFile = new JMenuItem("Open");
		        JMenuItem saveFile = new JMenuItem("Save");
		        JMenuItem exitEditor = new JMenuItem("Exit");

		        // Add action listeners for File menu items
		        newFile.addActionListener(this);
		        openFile.addActionListener(this);
		        saveFile.addActionListener(this);
		        exitEditor.addActionListener(this);

		        // Add menu items to file menu
		        fileMenu.add(newFile);
		        fileMenu.add(openFile);
		        fileMenu.add(saveFile);
		        fileMenu.add(exitEditor);

		        // Create Edit menu
		        JMenu editMenu = new JMenu("Edit");

		        // Create menu items for Edit menu
		        JMenuItem cutText = new JMenuItem("Cut");
		        JMenuItem copyText = new JMenuItem("Copy");
		        JMenuItem pasteText = new JMenuItem("Paste");
		        JMenuItem findReplace = new JMenuItem("Find and Replace");

		        // Add action listeners for Edit menu items
		        cutText.addActionListener(this);
		        copyText.addActionListener(this);
		        pasteText.addActionListener(this);
		        findReplace.addActionListener(this);

		        // Add menu items to edit menu
		        editMenu.add(cutText);
		        editMenu.add(copyText);
		        editMenu.add(pasteText);
		        editMenu.add(findReplace);

		        // Create Font menu
		        JMenu fontMenu = new JMenu("Font");

		        // Font family menu
		        JMenu fontFamilyMenu = new JMenu("Font Family");
		        for (String font : fonts) {
		            JMenuItem fontItem = new JMenuItem(font);
		            fontItem.addActionListener(this);
		            fontFamilyMenu.add(fontItem);
		        }

		        // Font size menu
		        JMenu fontSizeMenu = new JMenu("Font Size");
		        int[] sizes = {10, 12, 14, 16, 18, 20, 24, 28, 32, 36, 40};
		        for (int size : sizes) {
		            JMenuItem sizeItem = new JMenuItem(String.valueOf(size));
		            sizeItem.addActionListener(this);
		            fontSizeMenu.add(sizeItem);
		        }

		        // Font color option
		        JMenuItem colorPicker = new JMenuItem("Font Color");
		        colorPicker.addActionListener(this);

		        // Add font family, size, and color to Font menu
		        fontMenu.add(fontFamilyMenu);
		        fontMenu.add(fontSizeMenu);
		        fontMenu.add(colorPicker);

		        // Add menus to the menu bar
		        menuBar.add(fileMenu);
		        menuBar.add(editMenu);
		        menuBar.add(fontMenu);

		        // Set the menu bar to the frame
		        frame.setJMenuBar(menuBar);

		        // Initialize the text area with default font
		        textArea = new JTextArea();
		        textArea.setFont(new Font(defaultFontFamily, Font.PLAIN, defaultFontSize));

		        // Add scroll pane for the text area
		        JScrollPane scrollPane = new JScrollPane(textArea);
		        frame.add(scrollPane);

		        // Set default close operation
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		        // Set frame visibility
		        frame.setVisible(true);
		    }

		    // Action handling for menu items
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String command = e.getActionCommand();

		        if (command.equals("New")) {
		            // Clear the text area for a new file
		            textArea.setText("");
		        } 
		        else if (command.equals("Open")) {
		            // Open file dialog
		            JFileChooser fileChooser = new JFileChooser();
		            int option = fileChooser.showOpenDialog(this);
		            if (option == JFileChooser.APPROVE_OPTION) {
		                try {
		                    // Open the selected file and read its content
		                    File selectedFile = fileChooser.getSelectedFile();
		                    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
		                    textArea.read(reader, null);
		                    reader.close();
		                } catch (IOException ex) {
		                    ex.printStackTrace();
		                }
		            }
		        } 
		        else if (command.equals("Save")) {
		            // Save file dialog
		            JFileChooser fileChooser = new JFileChooser();
		            int option = fileChooser.showSaveDialog(this);
		            if (option == JFileChooser.APPROVE_OPTION) {
		                try {
		                    // Save the content of the text area to the selected file
		                    File selectedFile = fileChooser.getSelectedFile();
		                    BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
		                    textArea.write(writer);
		                    writer.close();
		                } catch (IOException ex) {
		                    ex.printStackTrace();
		                }
		            }
		        } 
		        else if (command.equals("Exit")) {
		            // Exit the application
		            System.exit(0);
		        }
		        // Edit functionalities
		        else if (command.equals("Cut")) {
		            textArea.cut();
		        } 
		        else if (command.equals("Copy")) {
		            textArea.copy();
		        } 
		        else if (command.equals("Paste")) {
		            textArea.paste();
		        } 
		        // Font styling functionalities
		        else if (command.equals("Font Color")) {
		            
		            Color color = JColorChooser.showDialog(this, "Choose Font Color", Color.BLACK);
		            if (color != null) {
		                textArea.setForeground(color);
		            }
		        } 
		        else if (isNumeric(command)) {
		           
		            int newSize = Integer.parseInt(command);
		            Font currentFont = textArea.getFont();
		            textArea.setFont(new Font(currentFont.getFontName(), Font.PLAIN, newSize));
		        } 
		        else {
		           
		            Font currentFont = textArea.getFont();
		            textArea.setFont(new Font(command, Font.PLAIN, currentFont.getSize()));
		        }
		    }

		    
		    private boolean isNumeric(String str) {
		        try {
		            Integer.parseInt(str);
		            return true;
		        } catch (NumberFormatException e) {
		            return false;
		        }
		        }

		   
		    public static void main(String[] args) {
		        new textEditor();
		    }
		}
