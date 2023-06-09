import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.lang.*;

/**The Signal class adds parameters to the signal.
 * @author Veronica Voitovich
 * @version 1.01 9 June 2023
 */
class Signal extends Component implements ActionListener {
    JFrame frame;
    JTextField textField, textField1, textField2, textField3, textField5;
    JButton bFind;
    JLabel l1, l2, l3, l4, l5, l6, l7;

    static double amp;
    double n;
    static double freq;
    static double phase;
    static double time;
    int identifier = 0;
    /**
     *The Signal constructor saves parameters of signal.
     */
    Signal() {
        frame = new JFrame("Signal Information");

        l1 = new JLabel("Сигнал: s[n]");
        l1.setBounds(50, 50, 300, 30);
        frame.add(l1);

        l5 = new JLabel("Введіть амплітуду");
        l5.setBounds(50, 100, 300, 30);
        frame.add(l5);

        textField3 = new JTextField();
        textField3.setBounds(50, 150, 200, 40);
        frame.add(textField3);

        l2 = new JLabel("Введіть частоту");
        l2.setBounds(50, 300, 300, 30);
        frame.add(l2);

        textField = new JTextField();
        textField.setBounds(50, 350, 200, 40);
        frame.add(textField);

        l3 = new JLabel("Введіть інтервал дискретизації");
        l3.setBounds(50, 200, 300, 30);
        frame.add(l3);

        textField1 = new JTextField();
        textField1.setBounds(50, 250, 200, 40);
        frame.add(textField1);

        l4 = new JLabel("Введіть фазу");
        l4.setBounds(50, 400, 300, 30);
        frame.add(l4);

        textField2 = new JTextField();
        textField2.setBounds(50, 450, 200, 40);
        frame.add(textField2);

        l7 = new JLabel("Змінити сигнал");
        l7.setBounds(300, 400, 300, 30);
        frame.add(l7);

        textField5 = new JTextField();
        textField5.setBounds(300, 450, 200, 40);
        frame.add(textField5);

        l6 = new JLabel("Сигнал: s[n]");
        l6.setBounds(50, 500, 600, 30);
        frame.add(l6);

        bFind = new JButton("Enter");
        bFind.setBounds(300, 50, 200, 40);
        frame.add(bFind);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(650, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        bFind.addActionListener(this);
    }
    /**
     *The method actionPerformed() return the result of signal.
     */
    public void actionPerformed(ActionEvent e) {

        freq = Double.parseDouble(textField.getText());
        time = Double.parseDouble(textField1.getText());
        amp = Double.parseDouble(textField3.getText());
        phase = Double.parseDouble(textField2.getText());

        double result = 0;
        if (e.getSource() == bFind) {
            result = amp*Math.sin(freq*n*time+phase);
            n++;
        }

        textField2.setText(String.valueOf(phase));
        textField5.setText(String.valueOf(result));

        l6.setText("s[" + n +"]"+ "= " + amp + " sin(" + freq +
                "*"+n+"*" + time + "*" + phase+")");
        l1.setText("Signal[n]: " + n);

        ArrayList<Object> arrayOfDifferentObject = new ArrayList<>();
        arrayOfDifferentObject.add(" Identifier: " + n);
        arrayOfDifferentObject.add(" Amplitude: " + amp);
        arrayOfDifferentObject.add(" Frequency: " + freq);
        arrayOfDifferentObject.add(" Phase " + phase);
        arrayOfDifferentObject.add(" Signal: " + result);

        System.out.println("signal list array: ");
        for (Object o : arrayOfDifferentObject) {
            System.out.println(o);
        }

        try {
            FileWriter writer = new FileWriter("text.txt");
            FileReader reader = new FileReader("text.txt");
            writer.write("signal list array: ");
            for (int i = 0; i < arrayOfDifferentObject.size(); i++) {
                writer.write(String.valueOf(arrayOfDifferentObject.get(i)));
            }
            Scanner scan = new Scanner(reader);
            int m = 1;
            while (scan.hasNextDouble()) {
                System.out.println(m + " : " + scan.nextDouble());
                m++;
            }
            reader.close();
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     *The construction Signal() return the identifier  of signal.
     */
    public Signal(int identifier, double n) {
        this.identifier = identifier;
        this.n = n;
    }
    /**
     *The method toString() return the parameters  of signal.
     */
    @Override
    public String toString() {
        return "s["+n+"]"+": \n Amplitude: "+ amp+ "\nPhase: "+phase+"\n Time: "+ time+ "\nPhase: "+phase;
    }

    public int getIdentifier() {
        return (int) n;
    }

    public void setIdentifier(int n) {
        this.n = n;
    }

    public double getQuantity() {
        return n;
    }

    public void setQuantity(double quantity) {
        this.n = quantity;
    }

    public double totalNumber() {
        return identifier * n;
    }

    public static void main(String[] args)  {
        Signal obj = new Signal();
        obj.setIdentifier(obj.getIdentifier());
        obj.setQuantity(obj.getQuantity());
        obj.toString();
        obj.totalNumber();

    }
}
