package com.syl;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SqlTool
{
    private JTextArea accountnameField = new JTextArea(70, 50);

    private JTextArea incomeField = new JTextArea(70, 50);

    private JTextArea incomeFromField = new JTextArea(70, 50);

    //DEAL
    private JButton DEALBtn = new JButton();
    //COPY
    private JButton COPYBtn = new JButton();
    //CLEAR
    private JButton CLEARBtn = new JButton();
    JFrame frame = new JFrame("sql-tool-wangjie");

    public static void main(String[] args)
    {
        try
        {
            new SqlTool().init();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void init()
            throws IOException
    {
        frame.getContentPane().add(getDialogMainPanel(), 0);
        Container con = frame.getContentPane();
        ((JPanel)con).setOpaque(false);
        frame.setSize(700, 500);
        frame.setLocation(200, 100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
    }

    public JPanel getDialogMainPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints nameLabelbagConstraints = new GridBagConstraints();
        nameLabelbagConstraints.gridx = 0;
        nameLabelbagConstraints.gridy = 0;
        nameLabelbagConstraints.insets = new Insets(10, 10, 0, 10);
        GridBagConstraints nameLTextFieldbagConstraints = new GridBagConstraints();
        nameLTextFieldbagConstraints.gridx = 1;
        nameLTextFieldbagConstraints.gridy = 0;
        nameLTextFieldbagConstraints.insets = new Insets(10, 10, 0, 40);
        nameLTextFieldbagConstraints.fill = 2;
        nameLTextFieldbagConstraints.weightx = 1.0D;
        GridBagConstraints incomeLabelbagConstraints = new GridBagConstraints();
        incomeLabelbagConstraints.gridx = 0;
        incomeLabelbagConstraints.gridy = 1;
        incomeLabelbagConstraints.insets = new Insets(10, 10, 0, 10);
        GridBagConstraints incomeLTextFieldbagConstraints = new GridBagConstraints();
        incomeLTextFieldbagConstraints.gridx = 1;
        incomeLTextFieldbagConstraints.gridy = 1;
        incomeLTextFieldbagConstraints.insets = new Insets(10, 10, 0, 40);
        incomeLTextFieldbagConstraints.fill = 2;
        incomeLTextFieldbagConstraints.weightx = 1.0D;
        GridBagConstraints incomeFromLabelbagConstraints = new GridBagConstraints();
        incomeFromLabelbagConstraints.gridx = 0;
        incomeFromLabelbagConstraints.gridy = 2;
        incomeFromLabelbagConstraints.insets = new Insets(10, 10, 0, 10);
        GridBagConstraints incomeFromLTextFieldbagConstraints = new GridBagConstraints();
        incomeFromLTextFieldbagConstraints.gridx = 1;
        incomeFromLTextFieldbagConstraints.gridy = 2;
        incomeFromLTextFieldbagConstraints.insets = new Insets(10, 10, 0, 40);
        incomeFromLTextFieldbagConstraints.fill = 2;
        incomeFromLTextFieldbagConstraints.weightx = 1.0D;
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 20;
        constraints.insets = new Insets(10, 10, 0, 20);

        //
        accountnameField.setLineWrap(true);
        JScrollPane jScrollPane1 = new JScrollPane(accountnameField);
        jScrollPane1.setMinimumSize(new Dimension(100, 100));
        incomeField.setLineWrap(true);
        JScrollPane jScrollPane2 = new JScrollPane(incomeField);
        jScrollPane2.setMinimumSize(new Dimension(100, 100));
        incomeFromField.setLineWrap(true);
        JScrollPane jScrollPane3 = new JScrollPane(incomeFromField);
        jScrollPane3.setMinimumSize(new Dimension(100, 100));
        panel.add(new JLabel("sql"), nameLabelbagConstraints);
        panel.add(jScrollPane1, nameLTextFieldbagConstraints);
        panel.add(new JLabel("params"), incomeLabelbagConstraints);
        panel.add(jScrollPane2, incomeLTextFieldbagConstraints);
        panel.add(new JLabel("result"), incomeFromLabelbagConstraints);
        panel.add(jScrollPane3, incomeFromLTextFieldbagConstraints);
        this.DEALBtn.setText("DEAL");
        this.DEALBtn.setSize(new Dimension(30, 30));
        this.DEALBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                String sql = SqlTool.this.accountnameField.getText();
                String params = SqlTool.this.incomeField.getText();
                SqlTool.this.incomeFromField.setText(SqlTool.this.dealSqlWithParams(sql, params));
            }
        });
        this.COPYBtn.setText("COPY");
        this.COPYBtn.setSize(new Dimension(30, 30));
        this.COPYBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                String tempText = incomeFromField.getText();

                StringSelection editText = new StringSelection(tempText);
                Clipboard clipboard = frame.getToolkit().getSystemClipboard();
                clipboard.setContents(editText,null);
            }
        });
        this.CLEARBtn.setText("CLEAR");
        this.CLEARBtn.setSize(new Dimension(30, 30));
        this.CLEARBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                accountnameField.setText("");
                incomeFromField.setText("");
                incomeField.setText("");
            }
        });
        JPanel panel2 = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        panel2.setLayout(flowLayout);
        panel2.add(DEALBtn);
        panel2.add(COPYBtn);
        panel2.add(CLEARBtn);
        panel.add(panel2, constraints);
        return panel;
    }

    /**
     * deal sql with params
     * @param sql
     * @param params
     * @return
     */
    protected String dealSqlWithParams(String sql, String params)
    {
        try
        {
            StringBuffer resultBuffer = new StringBuffer();

            String[] paramsArr = params.split(",");
            String[] params1Arr = new String[paramsArr.length];
            for (int i = 0; i < paramsArr.length; i++)
            {
                if (paramsArr[i].contains("("))
                {
                    String[] tempArr = paramsArr[i].split("\\(");
                    if (tempArr[1].contains("String"))
                    {
                        params1Arr[i] = ("'" + tempArr[0].trim() + "'");
                    }
                    else
                    {
                        params1Arr[i] = tempArr[0].trim();
                    }
                }
                else
                {
                    params1Arr[i] = paramsArr[i].trim();
                }
            }

            String[] sqlArr = sql.split("\\?");
            for (int j = 0; j < sqlArr.length; j++)
            {
                resultBuffer.append(sqlArr[j]);
                if (j < params1Arr.length)
                {
                    resultBuffer.append(params1Arr[j]);
                }
            }
            return resultBuffer.toString();
        }
        catch (Exception e)
        {
            return e.toString();
        }
    }
}