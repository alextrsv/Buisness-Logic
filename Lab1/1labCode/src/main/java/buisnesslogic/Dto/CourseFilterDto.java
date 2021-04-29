package buisnesslogic.Dto;

import buisnesslogic.entity.Category;

public class CourseFilterDto {
    Category category;
    int[] levels;
    String[] platforms;
    int leftDurationBorder;
    int rightDurationBorder;
    double markLeftBorder;
    double markRightBorder;


    public int getLeftDurationBorder() {
        return leftDurationBorder;
    }

    public void setLeftDurationBorder(int leftDurationBorder) {
        this.leftDurationBorder = leftDurationBorder;
    }

    public int getRightDurationBorder() {
        return rightDurationBorder;
    }

    public void setRightDurationBorder(int rightDurationBorder) {
        this.rightDurationBorder = rightDurationBorder;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int[] getLevels() {
        return levels;
    }

    public void setLevels(int[] levels) {
        this.levels = levels;
    }

    public String[] getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String[] platforms) {
        this.platforms = platforms;
    }

    public double getMarkLeftBorder() {
        return markLeftBorder;
    }

    public void setMarkLeftBorder(double markLeftBorder) {
        this.markLeftBorder = markLeftBorder;
    }

    public double getMarkRightBorder() {
        return markRightBorder;
    }

    public void setMarkRightBorder(double markRightBorder) {
        this.markRightBorder = markRightBorder;
    }
}
