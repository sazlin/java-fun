package com.seanazlin.gs1;

/* Student can give exams
exam has three steps. sequence of steps is fixed
1. take question paper : can't change this step
2. give answers : student can give any answers / own implementation
3. sign attendance : can't change this step
 */

public class ExamSteps {

    final public void takeExam(){
        takeQuestionPaper();
        giveAnswers();
        signAttendance();
    }

    final protected void takeQuestionPaper(){
        System.out.println("You took paper");
    }

    protected void giveAnswers(){
        System.out.println("You gave answers");
    }

    final protected void signAttendance(){
        System.out.println("You signed attendance");
    }

    public static void main(String[] args){
        ExamSteps examSteps = new ExamSteps();
        examSteps.takeExam();
    }
}

/*

 */