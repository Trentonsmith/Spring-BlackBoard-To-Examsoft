package com.example.bbtoes.Model;

public class Test {

    String testTitle;

    String testData;


    @Override
    public String toString() {
        return "Test{" +
                "testTitle='" + testTitle + '\'' +
                ", testData='" + testData + '\'' +
                '}';
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }
}
