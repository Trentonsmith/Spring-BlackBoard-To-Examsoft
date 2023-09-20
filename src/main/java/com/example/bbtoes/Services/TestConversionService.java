package com.example.bbtoes.Services;


import com.example.bbtoes.Model.Test;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ComponentScan
public class TestConversionService {

    public String testConvert(Test test) {


        String regex = "(Question [0-9]+[\\s\\S]*?\\n(?=Question [0-9]+|$))";

        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(test.getTestData());
        ArrayList<String> myList = new ArrayList<String>();
        ArrayList<Integer> jpgNeededList = new ArrayList<Integer>();

//        System.out.println(test.getTestData());

        while (m.find()) {
            //	System.out.println(m.group(0));
            if (m.group(0).contains(" \n \n \n \n \n ") || m.group(0).contains(" \n \n \n \n \n \n \n \n")) {
                myList.add(m.group(0).replaceAll("\\nFull credit given ", "").replaceAll(" \\n \\n \\n \\n \\n \\n \\n", "").replaceAll(" \\n \\n \\n \\n \\n ", " "));
                jpgNeededList.add(myList.size() - 1);
            } else {
                // System.out.println("found");
                myList.add(m.group(0).replaceAll("\\nFull credit given ", "").replaceAll(" \\n \\n \\n \\n \\n \\n \\n ", "").replaceAll(" \\n \\n \\n \\n \\n ", " ").replaceAll("  \\n \\n \\n \\n", " "));
                //  System.out.println(myList.get(0));
            }
        }
//        System.out.println("____________________________________");
//        System.out.println(myList.size());
//        System.out.println("----------------------");
//        System.out.println("Something");
//        for (String l :
//                myList) {
//            System.out.println(l);
//
//        }

        //	System.out.println(myList.get(7));

        ArrayList<Integer> numList = new ArrayList<Integer>();


        String quizNameFormatted = "Title: " + test.getTestTitle() + " ";
        m.reset();


        String regexMulitpleChoiceFourAnswers = "(Question [0-9]+?) *\\r\\n\\r\\n[0-9]+ *\\r\\n\\r\\npoints* *\\r\\n([\\s\\S]*? *\\r\\n \\r\\n|[\\s\\S]*? *\\r\\n+)(Answer 1 *\\r\\n\\r\\n.* *\\r\\n *\\r\\n[\\s\\S]*?)(Answer 2 *\\r\\n\\r\\n.* *\\r\\n *\\r\\n[\\s\\S]*?)(Answer 3 *\\r\\n\\r\\n.* *\\r\\n *\\r\\n[\\s\\S]*?)(Answer 4[\\s\\S]*)";


        Pattern pattern2 = Pattern.compile(regexMulitpleChoiceFourAnswers);


        String regexMultipleChoiceThreeAnswers = "(Question [0-9]+?) *\\n[0-9]+ *\\npoints* *\\n(.*? *\\n \\n|.*? *\\n+)(Answer 1 \\n.* \\n *\\n[\\s\\S]*?)(Answer 2 \\n.* \\n *\\n[\\s\\S]*?)(Answer 3[\\s\\S]*)";

        Pattern regexMultipleThreeAnswers = Pattern.compile(regexMultipleChoiceThreeAnswers);


        String regexTrueFalse = "(Question [0-9]+?) \\n[0-9]+ \\npoints* \\n(.*? *\\n *\\n|.*? *\\n)(True \\n[\\s\\S]*?)(False[\\s\\S]*)";


        Pattern trueFalsePattern = Pattern.compile(regexTrueFalse);
        StringBuilder sb = new StringBuilder();


        int revolution = 0;
        for (int i = 0; i < myList.size(); i++) {
        revolution++;

            m = pattern2.matcher(myList.get(i));


            Matcher trueFalsem = trueFalsePattern.matcher(myList.get(i));

            Matcher multipleChoiceThreem = regexMultipleThreeAnswers.matcher(myList.get(i));

            boolean isFourAnswers = false;


            while (m.find()) {

                sb.append(quizNameFormatted);
                int value = Integer.parseInt(m.group(1).replaceAll("[^0-9]", ""));
                numList.add(value);
                sb.append(value);
                sb.append(". ");
                sb.append(m.group(2).trim());
                sb.append("\n");

                sb.append(ifCorrectAppendAsteriskMultipleChoice(m.group(3), 'a'));
                sb.append(ifCorrectAppendAsteriskMultipleChoice(m.group(4), 'b'));
                sb.append(ifCorrectAppendAsteriskMultipleChoice(m.group(5), 'c'));
                sb.append(ifCorrectAppendAsteriskMultipleChoice(m.group(6), 'd'));
                sb.append("\n");
         //       System.out.println(sb);
                isFourAnswers = true;


            }
//					else if(m.group(0).matches("(Question [0-9]+?) \\n[0-9]+ \\npoints \\n(.*? *\\n \\n|.*? *\\n)(True \\n[\\s\\S]*? \\n)(False[\\s\\S]*)")) {
//
//
//
//						 sb.append(quizNameFormatted);
//			                int value = Integer.parseInt(m.group(2).replaceAll("[^0-9]", ""));
//			                numList.add(value);
//			                sb.append(value);
//			                sb.append(". ");
//			                sb.append(m.group(3).trim());
//			                sb.append("\n");
//
//			                sb.append(ifCorrectAppendAsteriskMultipleChoice(m.group(3),'a'));
//			                sb.append(ifCorrectAppendAsteriskMultipleChoice(m.group(4),'b'));
//
//
//						System.out.println(sb);
//
//					}

            if (!isFourAnswers) {
                while (multipleChoiceThreem.find()) {


                    int value = Integer.parseInt(multipleChoiceThreem.group(1).replaceAll("[^0-9]", ""));
                    numList.add(value);
                    sb.append(value);
                    sb.append(". ");
                    sb.append(multipleChoiceThreem.group(2).trim());
                    sb.append("\n");

                    sb.append(ifCorrectAppendAsteriskMultipleChoice(multipleChoiceThreem.group(3), 'a'));
                    sb.append(ifCorrectAppendAsteriskMultipleChoice(multipleChoiceThreem.group(4), 'b'));
                    sb.append(ifCorrectAppendAsteriskMultipleChoice(multipleChoiceThreem.group(5), 'c'));
                    System.out.println(sb);
                }
            }


            while (trueFalsem.find()) {


                sb.append(quizNameFormatted);
                int value = Integer.parseInt(trueFalsem.group(1).replaceAll("[^0-9]", ""));
                numList.add(value);
                sb.append(value);
                sb.append(". ");
                sb.append(trueFalsem.group(2).trim());
                sb.append("\n");

                sb.append(ifCorrectAppendAsteriskMultipleChoice(trueFalsem.group(3), 'a'));
                sb.append(ifCorrectAppendAsteriskMultipleChoice(trueFalsem.group(4), 'b'));


//                System.out.println(sb);
            }



        }
        System.out.println(sb);
        System.out.println(revolution);
        return sb.toString();
    }


    static String ifCorrectAppendAsteriskMultipleChoice(String question, char letterAnswer) {

        StringBuilder returned = new StringBuilder();

        if(question.contains("Correct answer")) {
            returned.append("*"+letterAnswer+". ");
            String removeAnswer = question.replaceAll("Answer [0-4]", "");
            String trimmed = removeAnswer.replaceAll("Correct answer", "").trim();
            returned.append(trimmed+"\n");
            return returned.toString();
        }
        else {

            String removeAnswer = letterAnswer+". "+(question.replaceAll("Answer [0-4]", "").trim())+"\n";
            return removeAnswer;
        }
    }

}


