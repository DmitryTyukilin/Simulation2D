package dimant.Labirint;

public class TestLabyrinth {
    public static void main(String[] args) {
        int[][] labyrinth = {{-1, -1, -2, -1},
                {-2, -1, -2, -1},
                {-1, -1, -1, -1},
                {-1, -2, -2, -1}};
        searchExit(1, 1, labyrinth);
    }

    public static void searchExit(int x, int y, int[][] lab) {
        int[][] copyLab = lab;
        int startPoint = 0;
        int currentStep = 1;
        copyLab[x][y] = startPoint;


        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab.length; j++) { //обход массива
                if (copyLab[i][j] == currentStep - 1) {
                    if (i > 0 && i < lab.length - 1 && j > 0 && j < lab.length - 1) {
                        if (copyLab[i - 1][j] == -1) {
                            copyLab[i - 1][j] = currentStep;
                        }
                        if (copyLab[i + 1][j] == -1) {
                            copyLab[i + 1][j] = currentStep;
                        }
                        if (copyLab[i][j + 1] == -1) {
                            copyLab[i][j + 1] = currentStep;
                        }
                        if (copyLab[i][j - 1] == -1)
                            copyLab[i][j - 1] = currentStep;
                    } else if (i == 0 && j < lab.length - 1) {
                        if (copyLab[i][j + 1] == -1) {
                            copyLab[i][j + 1] = currentStep;
                        }
                    } else if (i < lab.length - 1 && j == 0) {
                        if (copyLab[i + 1][j] == -1) {
                            copyLab[i + 1][j] = currentStep;
                        }
                    } else if (i < lab.length - 1 && j == lab.length - 1) {
                        if (copyLab[i + 1][j] == -1) {
                            copyLab[i + 1][j] = currentStep;
                        }
                        currentStep++;
                        if (currentStep < lab.length * lab[0].length) {
                            i = 0;
                        }
                    }

                }

            }
            System.out.println(
            );
        }
    }
}


