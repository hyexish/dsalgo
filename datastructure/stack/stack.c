#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * 栈快速实现
 * 定义： 
 *    int top=-1;
 *    int stack[n] = {0};
 * 操作：
 *   push  stack[++top] = val;
 *   pop   top--; or return stack[top--];
 *   peek  stack[top]
 *
 */
typedef struct stack{
  int *elements;
  int top;
  int capacity;
} stack;

void *init_stack(int capacity){
  stack *inst = (stack *)malloc(sizeof(stack));
  inst->elements = (int *)malloc(sizeof(int) * capacity);
  memset(inst->elements, 0,sizeof(int) * capacity);
  inst->capacity = capacity;
  inst->top = -1;
  return inst;
}

void push(stack *inst,int val){
  inst->elements[++(inst->top)] = val;
}

void pop(stack *inst){
  inst->top--;
}

int peek(stack *inst){
  return inst->elements[inst->top];
}

void print(stack *inst){
  for (int i = 0; i <= inst->top; i++) {
    printf("%d, ",inst->elements[i]);
  }
  printf("<-top\n");
}

void gc(stack *inst){
  free(inst->elements);
  free(inst);
}
int main(int argc, char *argv[])
{

  int capacity = 8;
  stack *inst = init_stack(capacity);
  push(inst, 1);
  push(inst, 2);
  push(inst, 3);

  print(inst);
  return 0;
}
