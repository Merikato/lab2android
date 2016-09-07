package seaimkhan.ca.loancalculator;

import android.net.ParseException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        String loanInput = ((EditText)findViewById(R.id.inputLoanAmount)).getText().toString();
        String yearInput = ((EditText)findViewById(R.id.inputLoanTerm)).getText().toString();
        String rateInput = ((EditText)findViewById(R.id.inputInterestRate)).getText().toString();

        try{
            double loanAmount = Double.parseDouble(loanInput);
            int loanTerm = Integer.parseInt(yearInput);
            double interestRate = Double.parseDouble(rateInput);

            if(interestRate > 100 || interestRate < 0)
                throw new IllegalArgumentException();

            if(loanTerm <= 0 || loanTerm > 25)
                throw new IllegalArgumentException();

            if(loanAmount < 0)
                throw new IllegalArgumentException();

            LoanCalculator calculator = new LoanCalculator(loanAmount, loanTerm, interestRate);

            double monthlyPayment = calculator.getMonthlyPayment();
            double totalPayment = calculator.getTotalCostOfLoan();
            double totalInterest = calculator.getTotalInterest();

            TextView txtMonthlyPayment = (TextView)findViewById(R.id.outputMonthlyPayment);
            TextView txtTotalPayment = (TextView)findViewById(R.id.outputTotalPayment);
            TextView txtTotalInterest = (TextView)findViewById(R.id.outputTotalInterest);

            txtMonthlyPayment.setText(""+monthlyPayment);
            txtTotalPayment.setText(""+totalPayment);
            txtTotalInterest.setText(""+totalInterest);

            TextView txtSuccess = (TextView)findViewById(R.id.txtErrorSuccess);
            txtSuccess.setText("Here are your results! Hope you don't have to pay much");
        }
        catch (IllegalArgumentException e) {
            TextView txtError = (TextView)findViewById(R.id.txtErrorSuccess);
            txtError.setText("ERROR: Wrong input!");
        }
    }

    public void onClear(View view){
        TextView txtMonthlyPayment = (TextView)findViewById(R.id.outputMonthlyPayment);
        TextView txtTotalPayment = (TextView)findViewById(R.id.outputTotalPayment);
        TextView txtTotalInterest = (TextView)findViewById(R.id.outputTotalInterest);
        TextView txtErrorSuccess = (TextView)findViewById(R.id.txtErrorSuccess);

        EditText loanInput = (EditText)findViewById(R.id.inputLoanAmount);
        EditText yearInput = (EditText)findViewById(R.id.inputLoanTerm);
        EditText rateInput = (EditText)findViewById(R.id.inputInterestRate);

        txtMonthlyPayment.setText("");
        txtTotalInterest.setText("");
        txtTotalPayment.setText("");
        txtErrorSuccess.setText("");
        loanInput.setText("");
        yearInput.setText("");
        rateInput.setText("");
    }
}
