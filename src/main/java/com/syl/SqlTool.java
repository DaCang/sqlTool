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
import javax.swing.*;

public class SqlTool
{
    /**
     * sql text area
     */
    private JTextArea sqlJTextArea = new JTextArea(70, 50);

    /**
     * params text area
     */
    private JTextArea paramsJTextArea = new JTextArea(70, 50);

    /**
     * result text area
     */
    private JTextArea resultJTextArea = new JTextArea(70, 50);


    /**
     * db connect form  area :ip
     */
    private JTextArea dbConnectIpFromArea = new JTextArea(70, 50);
    /**
     * db connect form  area :port
     */
    private JTextArea dbConnectPortFromArea = new JTextArea(70, 50);
    /**
     * db connect form  area :db
     */
    private JTextArea dbConnectDbFromArea = new JTextArea(70, 50);
    /**
     * db connect form  area :user and password
     */
    private JTextArea dbConnectUserAndPasswordFromArea = new JTextArea(70, 50);

    /**
     * deal btn
     */

    private JButton dealBtn = new JButton();

    /**
     * copy btn
     */
    private JButton copyBtn = new JButton();
   
    /**
     * clear btn
     */
    private JButton clearBtn = new JButton();
    /**
     * test connect btn
     */
    private JButton testConnectBtn = new JButton();
    JFrame frame = new JFrame("sqlTool_syl");

    /**
     * db connect
     */
    private DbConnect dbConnect;
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
        frame.getContentPane().add(getDbConnectPanel(), 0);
       /* if(){
            frame.getContentPane().add(getDialogMainPanel(), 1);
        }*/

        Container con = frame.getContentPane();
        ((JPanel)con).setOpaque(false);
        frame.setSize(900, 900);
        frame.setLocation(10, 20);
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
        constraints.gridy = 4;
        constraints.gridwidth = 20;
        constraints.insets = new Insets(10, 10, 0, 20);

        /**
         * text area
         */
        sqlJTextArea.setLineWrap(true);
        JScrollPane jScrollPane1 = new JScrollPane(sqlJTextArea);
        jScrollPane1.setMinimumSize(new Dimension(100, 100));

        paramsJTextArea.setLineWrap(true);
        JScrollPane jScrollPane2 = new JScrollPane(paramsJTextArea);
        jScrollPane2.setMinimumSize(new Dimension(100, 100));

        resultJTextArea.setLineWrap(true);
        JScrollPane jScrollPane3 = new JScrollPane(resultJTextArea);
        jScrollPane3.setMinimumSize(new Dimension(100, 100));


        panel.add(new JLabel("sql"), nameLabelbagConstraints);
        panel.add(jScrollPane1, nameLTextFieldbagConstraints);

        panel.add(new JLabel("params"), incomeLabelbagConstraints);
        panel.add(jScrollPane2, incomeLTextFieldbagConstraints);

        panel.add(new JLabel("result"), incomeFromLabelbagConstraints);
        panel.add(jScrollPane3, incomeFromLTextFieldbagConstraints);



        /**
         * btn
         */
        this.dealBtn.setText("deal");
        this.dealBtn.setSize(new Dimension(30, 30));
        this.dealBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                String sql = SqlTool.this.sqlJTextArea.getText();
                String params = SqlTool.this.paramsJTextArea.getText();
                SqlTool.this.resultJTextArea.setText(SqlTool.this.dealSqlWithParams(sql, params));
            }
        });
        this.copyBtn.setText("copy");
        this.copyBtn.setSize(new Dimension(30, 30));
        this.copyBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                String tempText = resultJTextArea.getText();

                StringSelection editText = new StringSelection(tempText);
                Clipboard clipboard = frame.getToolkit().getSystemClipboard();
                clipboard.setContents(editText,null);
            }
        });
        this.clearBtn.setText("clear");
        this.clearBtn.setSize(new Dimension(30, 30));
        this.clearBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                sqlJTextArea.setText("");
                resultJTextArea.setText("");
                paramsJTextArea.setText("");
            }
        });

        JPanel panel2 = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        panel2.setLayout(flowLayout);
        panel2.add(dealBtn);
        panel2.add(copyBtn);
        panel2.add(clearBtn);
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
    public JPanel getDbConnectPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        //jdbc.url=jdbc:mysql://47.95.20.204:3306/jims-his-jx?useUnicode=true&characterEncoding=utf-8
        //jdbc.username=root
        //jdbc.password=hongshizidb
        //ip label
        GridBagConstraints ipLabelGridBagConstraints = new GridBagConstraints();
        ipLabelGridBagConstraints.gridx = 0;
        ipLabelGridBagConstraints.gridy = 0;
        ipLabelGridBagConstraints.insets = new Insets(10, 10, 0, 10);
        //ip text
        GridBagConstraints ipTextFieldGridBagConstraints = new GridBagConstraints();
        ipTextFieldGridBagConstraints.gridx = 1;
        ipTextFieldGridBagConstraints.gridy = 0;
        ipTextFieldGridBagConstraints.insets = new Insets(10, 10, 0, 40);
        ipTextFieldGridBagConstraints.fill = 2;
        ipTextFieldGridBagConstraints.weightx = 1.0D;
        //port label
        GridBagConstraints portLabelGridBagConstraints = new GridBagConstraints();
        portLabelGridBagConstraints.gridx = 0;
        portLabelGridBagConstraints.gridy = 1;
        portLabelGridBagConstraints.insets = new Insets(10, 10, 0, 10);
        //port text
        GridBagConstraints portTextFieldGridBagConstraints = new GridBagConstraints();
        portTextFieldGridBagConstraints.gridx = 1;
        portTextFieldGridBagConstraints.gridy = 1;
        portTextFieldGridBagConstraints.insets = new Insets(10, 10, 0, 40);
        portTextFieldGridBagConstraints.fill = 2;
        portTextFieldGridBagConstraints.weightx = 1.0D;
        //db label
        GridBagConstraints dbLabelGridBagConstraints = new GridBagConstraints();
        dbLabelGridBagConstraints.gridx = 0;
        dbLabelGridBagConstraints.gridy = 2;
        dbLabelGridBagConstraints.insets = new Insets(10, 10, 0, 10);
        //db text
        GridBagConstraints dbTextFieldGridBagConstraints = new GridBagConstraints();
        dbTextFieldGridBagConstraints.gridx = 1;
        dbTextFieldGridBagConstraints.gridy = 2;
        dbTextFieldGridBagConstraints.insets = new Insets(10, 10, 0, 40);
        dbTextFieldGridBagConstraints.fill = 2;
        dbTextFieldGridBagConstraints.weightx = 1.0D;

        //user password label
        GridBagConstraints userAndPasswordLabelGridBagConstraints = new GridBagConstraints();
        userAndPasswordLabelGridBagConstraints.gridx = 0;
        userAndPasswordLabelGridBagConstraints.gridy = 3;
        userAndPasswordLabelGridBagConstraints.insets = new Insets(10, 10, 0, 10);

        //user password text
        GridBagConstraints userAndPasswordFieldGridBagConstraints= new GridBagConstraints();
        userAndPasswordFieldGridBagConstraints.gridx = 1;
        userAndPasswordFieldGridBagConstraints.gridy = 3;
        userAndPasswordFieldGridBagConstraints.insets = new Insets(10, 10, 0, 40);
        userAndPasswordFieldGridBagConstraints.fill = 2;
        userAndPasswordFieldGridBagConstraints.weightx = 1.0D;
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 20;
        constraints.insets = new Insets(10, 10, 0, 20);

        /**
         * text area
         */
        dbConnectIpFromArea.setLineWrap(true);
        JScrollPane jScrollPane1 = new JScrollPane(dbConnectIpFromArea);
        jScrollPane1.setMinimumSize(new Dimension(100, 100));

        dbConnectPortFromArea.setLineWrap(true);
        JScrollPane jScrollPane2 = new JScrollPane(dbConnectPortFromArea);
        jScrollPane2.setMinimumSize(new Dimension(100, 100));

        dbConnectDbFromArea.setLineWrap(true);
        JScrollPane jScrollPane3 = new JScrollPane(dbConnectDbFromArea);
        jScrollPane3.setMinimumSize(new Dimension(100, 100));

        dbConnectUserAndPasswordFromArea.setLineWrap(true);
        JScrollPane jScrollPane4 = new JScrollPane(dbConnectUserAndPasswordFromArea);
        jScrollPane1.setMinimumSize(new Dimension(100, 100));

        panel.add(new JLabel("ip"), ipLabelGridBagConstraints);
        panel.add(jScrollPane1, ipTextFieldGridBagConstraints);

        panel.add(new JLabel("port"), portLabelGridBagConstraints);
        panel.add(jScrollPane2, portTextFieldGridBagConstraints);

        panel.add(new JLabel("db"), dbLabelGridBagConstraints);
        panel.add(jScrollPane3, dbTextFieldGridBagConstraints);


        panel.add(new JLabel("user/password"), userAndPasswordLabelGridBagConstraints);
        panel.add(jScrollPane4, userAndPasswordFieldGridBagConstraints);

        this.testConnectBtn.setText("test connect");
        this.testConnectBtn.setSize(new Dimension(30, 30));
        this.testConnectBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                String ip = dbConnectIpFromArea.getText();
                String port = dbConnectPortFromArea.getText();
                String db = dbConnectDbFromArea.getText();
                String userAndPassword = dbConnectUserAndPasswordFromArea.getText();
                String url="jdbc:mysql://"+ip+":"+port+"/"+db+"?useUnicode=true&characterEncoding=utf-8";
                String []up= userAndPassword.split("/");
                String username = up[0];
                String password = up[1];
                dbConnect =new DbConnect();
                dbConnect.setUrl(url);
                dbConnect.setUsername(username);
                dbConnect.setPassword(password);
                if(dbConnect.testConnect()){

                    JOptionPane.showConfirmDialog(null, "连接成功~！", "是否继续", JOptionPane.YES_NO_OPTION);
                }
                System.out.println(ip+":"+port+"/"+db+userAndPassword);
            }
        });
        JPanel panel2 = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        panel2.setLayout(flowLayout);
        panel2.add(testConnectBtn);
        panel.add(panel2, constraints);
        return panel;
    }
}