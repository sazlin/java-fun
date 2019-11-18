package com.seanazlin.gs1;

public class StudentExamSteps extends ExamSteps {
    @Override
    protected void giveAnswers(){
        System.out.println("You gave a student's answers");
    }

    public static void main(String[] args){
        ExamSteps exam = new StudentExamSteps();
        exam.takeExam();
    }

}
