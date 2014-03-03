#include <cstdio>
#include <cstdlib>

#define WA 0
#define AC 1
#define PE 2
#define SE 3
#define EPS 1e-8

/**
 * @brief
 *    Basic judge item for judger, store stdard input and output, user output
 *  FILE* entity.
 */
struct Node {
  FILE* data_input;
  FILE* data_output;
  FILE* user_output;
};

static Node* node_pointer;

/**
 * @brief
 *    if statement is not true, print exit_code's string and exit SPJ
 */
void dump(bool statement, const int& exit_code) {
  if (statement)
    return;
  if (node_pointer->data_input != NULL)
    fclose(node_pointer->data_input);
  if (node_pointer->data_output != NULL)
    fclose(node_pointer->data_output);
  if (node_pointer->user_output != NULL)
    fclose(node_pointer->user_output);
  if (exit_code == WA)
    puts("WA");
  else if (exit_code == AC)
    puts("AC");
  else if (exit_code == PE)
    puts("PE");
  else
    puts("SE");
  delete node_pointer;
  exit(0);
}

int nextInt(FILE* fp) {
  int variable;
  dump(fscanf(fp, "%d", &variable) == 1, fp == stdin ? WA : SE);
  return variable;
}

long long nextLong(FILE* fp) {
  long long variable;
  dump(fscanf(fp, "%lld", &variable) == 1, fp == stdin ? WA : SE);
  return variable;
}

double nextDouble(FILE* fp) {
  double variable;
  dump(fscanf(fp, "%lf", &variable) == 1, fp == stdin ? WA : SE);
  return variable;
}

void nextString(FILE* fp, char* buf) {
  dump(fscanf(fp, "%s", buf) == 1, fp == stdin ? WA : SE);
}

int sgn(double variable) {
  return variable < -EPS ? -1 : variable > EPS;
}

int compare(double lhs, double rhs) {
  return sgn(lhs - rhs);
}

/**
 * @brief
 *    Judge for specific problem, only function needed to modify.
 * @author RuinsHe<lyhypacm@gmail.com>
 */
void judge() {
}

int main(int argc, char* argv[]) {
  node_pointer = new Node();
  dump(argc >= 3, SE);
  node_pointer->data_input = fopen(argv[1], "r");
  dump(node_pointer->data_input != NULL, SE);
  node_pointer->data_output = fopen(argv[2], "r");
  dump(node_pointer->data_output != NULL, SE);
  node_pointer->user_output = stdin;
  dump(node_pointer->user_output != NULL, SE);
  judge();
  dump(false, AC);
  return 0;
}