package br.com.robertodebarba.rd_compiler.compiler;

interface ParserConstants {
    int START_SYMBOL = 47;

    int FIRST_NON_TERMINAL = 47;
    int FIRST_SEMANTIC_ACTION = 95;

    int[][] PARSER_TABLE = {
            {-1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 2, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, 5, -1, -1, -1, -1, 6, -1, -1, -1, 5, -1, 6, -1, -1, -1, 5, 5, -1, -1, -1, -1, -1, -1, 5, 5, 5, 6, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1},
            {-1, 7, -1, -1, -1, -1, -1, -1, -1, -1, 9, -1, -1, -1, -1, -1, 12, 8, -1, -1, -1, -1, -1, -1, 11, 13, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, 14, -1, -1, -1, -1, -1, -1, 15, -1},
            {17, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 16, -1, -1, -1, -1, -1, -1, -1, 16, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 18, -1, -1, -1, -1, -1, -1, -1, 19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 20, -1, -1, -1, -1, -1, -1, -1, 21, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 22, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 23, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, 24, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, 25},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    26, -1, -1, -1, -1, -1, -1, -1, -1, 27},
            {-1, 28, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, 29, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 30, 31,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, 31},
            {-1, -1, -1, -1, -1, -1, -1, 32, -1, -1, -1, -1, -1, -1, -1, 34, -1, -1, 33, -1, -1, -1, -1, 35, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, 37, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, 39, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 38, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 41, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 42, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, 43, 43, 43, 43, -1, -1, -1, -1, -1, -1, 43, -1, -1, -1, -1, -1, -1, -1, 43, -1, -1, 43, -1, -1, -1, -1, -1, -1, 43, 43, 43, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, 43, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 44, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, 45},
            {-1, 46, 46, 46, 46, -1, -1, -1, -1, -1, -1, 46, -1, -1, -1, -1, -1, -1, -1, 46, -1, -1, 47, -1, -1, -1, -1, -1, -1, 46, 46, 46, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, 46, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 48, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 50, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 49, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 52, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 53, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, 54, 54, 54, 54, -1, -1, -1, -1, -1, -1, 54, -1, -1, -1, -1, -1, -1, -1, 54, -1, -1, -1, -1, -1, -1, -1, -1, -1, 54, 54, 54, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, 54, 55},
            {-1, 56, 56, 56, 56, -1, -1, -1, -1, -1, -1, 56, -1, -1, -1, -1, -1, -1, -1, 56, -1, -1, -1, -1, -1, -1, -1, -1, -1, 56, 56, 56, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, 56, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 57, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, 58},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 59, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 60, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, 61, 61, 61, 61, -1, -1, -1, -1, -1, -1, 61, -1, -1, -1, -1, -1, -1, -1, 61, -1, -1, -1, -1, -1, -1, -1, -1, -1, 61, 61, 61, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, 61, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, 63, 62, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 64, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1,
                    62, -1, -1, -1, -1, -1, -1, -1, -1, 62},
            {-1, 65, 65, 65, 65, -1, -1, -1, -1, -1, -1, 67, -1, -1, -1, -1, -1, -1, -1, 68, -1, -1, -1, -1, -1, -1, -1, -1, -1, 66, 65, 65, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, 65, -1},
            {-1, 69, 69, 69, 69, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 69, 69, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, 69, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, 71, 71, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 71, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 71, -1,
                    71, -1, 70, 70, 70, 70, 70, 70, -1, 71},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, 72, 73, 74, 75, 76, 77, -1, -1},
            {-1, 78, 78, 78, 78, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 78, 78, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, 78, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, 79, 79, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 79, -1, -1, -1, -1, -1, -1, -1, -1, -1, 80, 81, -1, -1, 79, -1,
                    79, -1, 79, 79, 79, 79, 79, 79, -1, 79},
            {-1, 82, 82, 82, 82, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 82, 82, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, 82, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, 83, 83, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 83, -1, -1, -1, -1, -1, -1, -1, -1, -1, 83, 83, 84, 85, 83, -1,
                    83, -1, 83, 83, 83, 83, 83, 83, -1, 83},
            {-1, 86, 87, 88, 89, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 91, 92, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, 90, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, 93, 93, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 93, -1, -1, -1, -1, -1, -1, -1, -1, -1, 93, 93, 93, 93, 93, -1,
                    93, -1, 93, 93, 93, 93, 93, 93, 94, 93}};

    int[][] PRODUCTIONS = {{110, 6, 5, 48, 49, 15, 50, 13, 111, 53, 112}, {55, 48}, {0}, {64}, {0}, {51, 37, 50}, {0}, {2, 118, 52}, {68},
            {69}, {73}, {75}, {76}, {77}, {38, 83, 122}, {45, 78, 46, 133}, {54, 53}, {0}, {81}, {82}, {56}, {57},
            {14, 2, 128, 45, 58, 46, 36, 63, 117, 129, 37}, {22, 2, 128, 45, 58, 46, 130, 37}, {60, 59}, {0}, {37, 60, 59}, {0},
            {61, 36, 63, 117, 131}, {2, 118, 62}, {35, 2, 118, 62}, {0}, {8}, {19}, {16}, {24}, {29, 65}, {67, 66}, {0}, {65},
            {61, 36, 63, 117, 119, 37}, {18, 45, 61, 120, 46}, {11, 45, 70, 46}, {72, 102, 71}, {35, 72, 102, 71}, {0}, {83}, {23, 113},
            {27, 83, 123, 10, 50, 74, 13, 124}, {28, 125, 50}, {0}, {25, 126, 50, 7, 83, 127}, {17}, {26, 83}, {79}, {0}, {83, 80},
            {35, 83, 80}, {0}, {14, 2, 132, 37, 49, 15, 50, 13, 111}, {22, 2, 132, 37, 49, 15, 50, 13, 111}, {85, 84}, {0}, {9, 85, 114, 84},
            {21, 85, 115, 84}, {86}, {30, 107}, {12, 108}, {20, 85, 109}, {89, 87}, {88, 106, 89, 105}, {0}, {39}, {40}, {41}, {42},
            {43}, {44}, {91, 90}, {0}, {31, 91, 96, 90}, {32, 91, 97, 90}, {93, 92}, {0}, {33, 93, 98, 92}, {34, 93, 99, 92},
            {2, 118, 94}, {3, 100}, {4, 101}, {5, 116}, {45, 83, 46}, {31, 93, 103}, {32, 93, 104}, {121}, {45, 78, 46, 134}};

    String[] PARSER_ERROR = {"", "encontrado %s esperado fim de arquivo", "encontrado %s esperado identificador", "encontrado %s esperado constante inteira",
            "encontrado %s esperado constante real", "encontrado %s esperado constante caracter", "encontrado %s esperado algoritmo",
            "encontrado %s esperado até", "encontrado %s esperado caracter", "encontrado %s esperado e", "encontrado %s esperado então",
            "encontrado %s esperado escreva", "encontrado %s esperado falso", "encontrado %s esperado fim", "encontrado %s esperado função",
            "encontrado %s esperado início", "encontrado %s esperado inteiro", "encontrado %s esperado interrompa", "encontrado %s esperado leia",
            "encontrado %s esperado lógico", "encontrado %s esperado não", "encontrado %s esperado ou", "encontrado %s esperado procedimento",
            "encontrado %s esperado quebra", "encontrado %s esperado real", "encontrado %s esperado repita", "encontrado %s esperado retorne",
            "encontrado %s esperado se", "encontrado %s esperado senão", "encontrado %s esperado variáveis", "encontrado %s esperado verdadeiro",
            "encontrado %s esperado +", "encontrado %s esperado -", "encontrado %s esperado *", "encontrado %s esperado /", "encontrado %s esperado ,",
            "encontrado %s esperado :", "encontrado %s esperado ;", "encontrado %s esperado <-", "encontrado %s esperado =", "encontrado %s esperado <>",
            "encontrado %s esperado <", "encontrado %s esperado <=", "encontrado %s esperado >", "encontrado %s esperado >=", "encontrado %s esperado (",
            "encontrado %s esperado )", "encontrado %s esperado algoritmo", // "<programa>
            // inválido",
            "encontrado %s esperado função início procedimento variáveis", // "<cabecalho_modulos>
            // inválido",
            "encontrado %s esperado início variáveis", // "<variaveis_opcional>
            // inválido",
            "encontrado %s esperado identificador até escreva fim interrompa leia repita retorne se senão", // "<lista_comandos>
            // inválido",
            "encontrado %s esperado identificador escreva interrompa leia repita retorne se", // "<comando>
            // inválido",
            "encontrado %s esperado <- (", // "<comando_1> inválido",
            "encontrado %s esperado fim de arquivo função procedimento", // "<lista_modulos>
            // inválido",
            "encontrado %s esperado função procedimento", // "<modulo>
            // inválido",
            "encontrado %s esperado função procedimento", // "<cabecalho>
            // inválido",
            "encontrado %s esperado função", // "<funcao_cabecalho> inválido",
            "encontrado %s esperado procedimento", // "<procedimento_cabecalho>
            // inválido",
            "encontrado %s esperado identificador )", // "<lista_parametros>
            // inválido",
            "encontrado %s esperado ; )", // "<lista_parametros_adicionais>
            // inválido",
            "encontrado %s esperado identificador", // "<parametro> inválido",
            "encontrado %s esperado identificador", // "<lista_identificadores>
            // inválido",
            "encontrado %s esperado , : )", // "<lista_identificadores_adicionais>
            // inválido",
            "encontrado %s esperado caracter inteiro lógico real", // "<tipo>
            // inválido",
            "encontrado %s esperado variáveis", // "<variaveis> inválido",
            "encontrado %s esperado identificador", // "<lista_variaveis>
            // inválido",
            "encontrado %s esperado identificafor início", // "<lista_variaveis_1>
            // inválido",
            "encontrado %s esperado identificador", // "<variavel> inválido",
            "encontrado %s esperado leia", // "<entrada_dados> inválido",
            "encontrado %s esperado escreva", // "<saida_dados> inválido",
            "encontrado %s esperada expressão", // <lista_valores> inválido",
            "encontrado %s esperado , )", // "<lista_valores_adicionais>
            // inválido",
            "encontrado %s esperado expressão quebra", // "<valor> inválido",
            "encontrado %s esperado se", // "<selecao> inválido",
            "encontrado %s esperado fim, senão", // "<senao_opcional> inválido",
            "encontrado %s esperado repita", // "<repeticao> inválido",
            "encontrado %s esperado interrompa", // "<interrompa> inválido",
            "encontrado %s esperado retorne", // "<retorne> inválido",
            "encontrado %s esperada expressão )", // "<parametros_reais>
            // inválido",
            "encontrado %s esperada expressão", // "<lista_expressoes>
            // inválido",
            "encontrado %s esperado , )", // "<lista_expressoes_adicionais>
            // inválido",
            "encontrado %s esperado função", // "<funcao> inválido",
            "encontrado %s esperado procedimento", // "<procedimento> inválido",
            "encontrado %s esperado expressão", // "<expressao> inválido",
            "encontrado %s esperado expressão", // "<expressao_1> inválido",
            "encontrado %s esperado expressão", // "<elemento> inválido",
            "encontrado %s esperado expressão", // "<relacional> inválido",
            "encontrado %s esperado expressão", // "<relacional_1> inválido",
            "encontrado %s esperado expressão", // "<operador_relacional>
            // inválido",
            "encontrado %s esperado expressão", // "<aritmetica> inválido",
            "encontrado %s esperado expressão", // "<aritmetica_1> inválido",
            "encontrado %s esperado expressão", // "<termo> inválido",
            "encontrado %s esperado expressão", // "<termo_1> inválido",
            "encontrado %s esperado expressão", // "<fator> inválido",
            "encontrado %s esperado expressão", // "<fator_1> inválido"
    };
}