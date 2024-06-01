package com.yagnikfadadu.myquiz;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private String[] questions = {
            "What is the capital city of France?",
            "Who wrote the play 'Romeo and Juliet'?",
            "What is the largest planet in our Solar System?",
            "Who was the first President of the United States?",
            "What is the chemical symbol for water?"
    };

    private String[][] options = {
            {"Berlin", "Madrid", "Paris", "Rome"},
            {"William Shakespeare", "Mark Twain", "Charles Dickens", "Jane Austen"},
            {"Earth", "Mars", "Jupiter", "Saturn"},
            {"Thomas Jefferson", "John Adams", "Abraham Lincoln", "George Washington"},
            {"O2", "CO2", "H2O", "NaCl"}
    };

    private int[] answers = {2, 0, 2, 3, 2}; // Correct option indices

    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private RadioButton[] optionButtons;
    private Button nextButton;
    private TextView scoreTextView;

    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.question_text_view);
        optionsRadioGroup = findViewById(R.id.options_radio_group);
        optionButtons = new RadioButton[4];
        optionButtons[0] = findViewById(R.id.option1_radio_button);
        optionButtons[1] = findViewById(R.id.option2_radio_button);
        optionButtons[2] = findViewById(R.id.option3_radio_button);
        optionButtons[3] = findViewById(R.id.option4_radio_button);
        nextButton = findViewById(R.id.next_button);
        scoreTextView = findViewById(R.id.score_text_view);

        displayQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    displayQuestion();
                } else {
                    displayScore();
                }
            }
        });
    }

    private void displayQuestion() {
        questionTextView.setText(questions[currentQuestionIndex]);
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setText(options[currentQuestionIndex][i]);
        }
        optionsRadioGroup.clearCheck();
    }

    private void checkAnswer() {
        int selectedOptionIndex = optionsRadioGroup.indexOfChild(findViewById(optionsRadioGroup.getCheckedRadioButtonId()));
        if (selectedOptionIndex == answers[currentQuestionIndex]) {
            score++;
        }
    }

    private void displayScore() {
        questionTextView.setVisibility(View.GONE);
        optionsRadioGroup.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);

        scoreTextView.setVisibility(View.VISIBLE);
        scoreTextView.setText("Your score: " + score + "/" + questions.length);
    }
}
