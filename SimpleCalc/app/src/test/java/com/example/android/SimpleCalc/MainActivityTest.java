package com.example.android.SimpleCalc;

import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import android.text.Editable;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {

    @Mock
    private EditText mockOperandOneEditText;

    @Mock
    private EditText mockOperandTwoEditText;

    @Mock
    private TextView mockResultTextView;

    @Mock
    private Context mockContext;

    @Mock
    private Editable mockEditable1;

    @Mock
    private Editable mockEditable2;

    private MainActivity mainActivity;

    @Before
    public void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Create MainActivity instance
        mainActivity = spy(new MainActivity());
        mainActivity.setTestMode(true);  // Disable toast for testing

        // Mock getText() to return Editable
        when(mockOperandOneEditText.getText()).thenReturn(mockEditable1);
        when(mockOperandTwoEditText.getText()).thenReturn(mockEditable2);

        // Mock toString() for the Editables
        when(mockEditable1.toString()).thenReturn("32.0");
        when(mockEditable2.toString()).thenReturn("16.0");

        // Mock getString() method for error messages
        doReturn("Error").when(mainActivity).getString(R.string.computationError);

        try {
            // Create a Calculator instance first
            Calculator calculator = new Calculator();

            // Set private fields using reflection
            java.lang.reflect.Field calculatorField = MainActivity.class.getDeclaredField("mCalculator");
            calculatorField.setAccessible(true);
            calculatorField.set(mainActivity, calculator);  // Use the created calculator instance

            java.lang.reflect.Field resultField = MainActivity.class.getDeclaredField("mResultTextView");
            resultField.setAccessible(true);
            resultField.set(mainActivity, mockResultTextView);

            java.lang.reflect.Field operandOneField = MainActivity.class.getDeclaredField("mOperandOneEditText");
            operandOneField.setAccessible(true);
            operandOneField.set(mainActivity, mockOperandOneEditText);

            java.lang.reflect.Field operandTwoField = MainActivity.class.getDeclaredField("mOperandTwoEditText");
            operandTwoField.setAccessible(true);
            operandTwoField.set(mainActivity, mockOperandTwoEditText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddOperation() {
        // Act
        mainActivity.onAdd(new View(mockContext));

        // Assert
        verify(mockResultTextView).setText("48.0");
    }

    @Test
    public void testSubOperation() {
        // Act
        mainActivity.onSub(new View(mockContext));

        // Assert
        verify(mockResultTextView).setText("16.0");
    }

    @Test
    public void testDivOperation() {
        // Act
        mainActivity.onDiv(new View(mockContext));

        // Assert
        verify(mockResultTextView).setText("2.0");
    }

    @Test
    public void testDivByZero() {
        // Arrange
        when(mockEditable2.toString()).thenReturn("0.0");

        // Act
        mainActivity.onDiv(new View(mockContext));

        // Assert
        verify(mockResultTextView).setText("Cannot divide by zero");
    }

    @Test
    public void testMulOperation() {
        // Act
        mainActivity.onMul(new View(mockContext));

        // Assert
        verify(mockResultTextView).setText("512.0");
    }

    @Test
    public void testInvalidInput() {
        // Arrange
        when(mockEditable1.toString()).thenReturn("invalid");

        // Act
        mainActivity.onAdd(new View(mockContext));

        // Assert
        verify(mockResultTextView).setText("Error");
    }
}