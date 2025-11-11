package csd214_fall2025;

import csd214_fall2025.pojos.Editable;
import csd214_fall2025.pojos.SaleableItem;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class App_bu {
    private final String menu = "\n***********************\n"
            + " 1. Add Items\n"
            + " 2. Edit Items\n"
            + " 3. Delete Items\n"
            + " 4. Sell item(s)\n"
            + " 5. List items\n"
            + "99. Quit\n"
            + "***********************\n"
            + "Enter choice: ";
    //private final SaleableItem[] saleableItems = new SaleableItem[MAX_ITEMS];
//    private final ArrayList<SaleableItem> saleableItems = new ArrayList<>();
    private final int currentItem = 0;

    private Scanner input;
    private final PrintStream out;

    // Default constructor for normal execution
    public App_bu() {
        this(System.in, System.out);
    }
    // Constructor for testing
    public App_bu(InputStream in, PrintStream out) {
        this.input = new Scanner(in);
        this.out = out;
    }

    public void run(){};
    public boolean findItemExists(SaleableItem item){return false;};
    public SaleableItem findItem(SaleableItem item){return null;}
    public void editItem(){}
    public void editItem(Editable item){}
    public void deleteItem(){}
    public void populate(){}
    public void listAny(){}
    public SaleableItem getItem(SaleableItem item){return null;}
    public void sellItem(){}
    public void listI(Object items){}
    public void addItem(){}
    public void addItem(SaleableItem item){}

}
