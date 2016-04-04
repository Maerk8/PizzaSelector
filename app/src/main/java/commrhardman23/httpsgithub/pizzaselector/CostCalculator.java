package commrhardman23.httpsgithub.pizzaselector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CostCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_calculator);

        //constants of cost values
        final double TOPPING_COST = 0.50;
        final double INDIVIDUAL_COST = 8.99;
        final double SMALL_COST = 13.49;
        final double MEDIUM_COST = 20.99;
        final double LARGE_COST = 23.99;
        final double EXTRA_COST = 27.99;
        final double GARLIC_CRUST = 2.00;
        final double THIN_CRUST = 0.00;
        final double THICK_CRUST = 1.50;
        final double CHEESE_FILLED = 3.00;

        //receive information from previous activity by getting extras from Intent
        boolean[] toppingsOnPizza = getIntent().getBooleanArrayExtra("TOPPINGS_BOOLEANS");
        String sizeName = getIntent().getStringExtra("SIZE_SELECTION");
        String crustSelection = getIntent().getStringExtra("CRUST_SELECTION");
        boolean hasGarlicCrust = getIntent().getBooleanExtra("HAS_GARLIC_CRUST", false);

        //other information that will be needed for displaying totals to user
        int numToppings = 0;
        double toppingCost = 0.0;
        double sizeCost = 0.0;
        String crustName = ""; //will contain both size and whether the crust was garlic crust
        double crustCost = 0.0;
        double subtotal = 0.0;
        double taxes = 0.0;
        double totalCost = 0.0;

        TextView txtvwCostBreakdown = (TextView) findViewById(R.id.txtvwCostBreakdown);

        //Insert your code here.
        for (int i = 0; i < toppingsOnPizza.length; i++) {
            if(toppingsOnPizza[i] == true){
                numToppings++;
                toppingCost += TOPPING_COST;
            }
        }

        if (sizeName.equals("Individual")) {
            sizeCost = INDIVIDUAL_COST;}
        else if (sizeName.equals("Small Pizza")) {
            sizeCost = SMALL_COST;}
        else if (sizeName.equals("Medium Pizza")) {
            sizeCost = MEDIUM_COST;}
        else if (sizeName.equals("Large Pizza")) {
            sizeCost = LARGE_COST;}
        else if (sizeName.equals("Extra Large Pizza")) {
            sizeCost = EXTRA_COST;}
        else {
            sizeCost = 0.00;
            sizeName = "Nothing Selected";}



        if (crustSelection.equals("Thin")) {
            crustCost += THIN_CRUST;
            crustName = "Thin Crust";
            if (hasGarlicCrust == true) {
                crustCost += GARLIC_CRUST;
                crustName = "Thin Crust with Garlic";
            }
        }
        else if (crustSelection.equals("Thick")) {
            crustCost += THICK_CRUST;
            crustName = "Thick Crust";
            if (hasGarlicCrust == true) {
                crustCost += GARLIC_CRUST;
                crustName = "Thick Crust with Garlic";}
        }
        else if (crustSelection.equals("Cheese")) {
            crustCost += CHEESE_FILLED;
            crustName = "Cheese Crust";
            if (hasGarlicCrust == true) {
                crustCost += GARLIC_CRUST;
                crustName = "Cheese Filled Crust with Garlic";}
        }
        else {
            crustCost = 0.00;
            crustName = "Nothing Selected.";
        }

        subtotal = crustCost + sizeCost + toppingCost;
        taxes = subtotal * 0.13;
        totalCost = subtotal + taxes;







        String costs = String.format("Toppings: %d x $0.75 = $%.2f\nSize: %s = $%.2f\n" +
                "Crust Type: %s = $%.2f\nSubtotal: $%.2f\nTaxes: $%.2f\nTotal: $%.2f",
                numToppings, toppingCost, sizeName, sizeCost, crustName, crustCost,
                subtotal, taxes, totalCost);

        txtvwCostBreakdown.setText(costs);

    }

    public void backToMenu(View vw){

        Intent makeNewPizza = new Intent();
        setResult(0, makeNewPizza);
        finish();

    }
}
