#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// 动态数组
typedef struct dynamic_array {
  int *elements;
  int capacity;
  int size;
} dynamic_array;

dynamic_array *create_dynamic_array(int capacity) {
  dynamic_array *inst = (dynamic_array *)malloc(sizeof(dynamic_array));
  inst->elements = (int *)malloc(sizeof(int) * capacity);
  inst->capacity = capacity;
  inst->size = 0;
  memset(inst->elements, 0, capacity);
  return inst;
}
void expand_capacity(dynamic_array *inst) {
  int new_capacity = inst->capacity << 1;
  int *dest = (int *)malloc(sizeof(int) * new_capacity);
  memset(dest, 0, new_capacity);
  memmove(dest,inst->elements,sizeof(int) * inst->capacity);
  inst->capacity = new_capacity;
  inst->elements = dest;
}
int empty(dynamic_array *inst) { return inst->size == 0 ? 0 : 1; }
int full(dynamic_array *inst) { return inst->size == inst->capacity ? 1 : 0; }

void append(dynamic_array *inst, int val) {
  if (full(inst)) {
    expand_capacity(inst);
  }
  inst->elements[inst->size++] = val;
}

void insert(dynamic_array *inst, int idx, int val) {
  if (full(inst)) {
    expand_capacity(inst);
  }
  for(int i = inst->size ; i > idx; i--){
     int tmp = inst->elements[i];
     inst->elements[i] = inst->elements[i - 1];
     inst->elements[i - 1] = tmp; 
  }
  inst->elements[idx] = val;
  inst->size++;
}
void update(dynamic_array *inst, int idx, int val) {
  inst->elements[idx] = val;
}
void delete(dynamic_array *inst, int idx) {
  for(int i = idx; i < inst->size -1; i++){
     int tmp = inst->elements[i];
     inst->elements[i] = inst->elements[i + 1];
     inst->elements[i + 1] = tmp; 
  }
  inst->size--;
}
int search(dynamic_array *inst, int val) {
  int i;
  for (i = 0; inst->elements[i] == val && i < inst->size; i++) {
    return i;
  }
  return -1;
}

void clear(dynamic_array *inst) {
  memset(inst, 0, inst->size);
  inst->size = 0;
}

void gc(dynamic_array *inst) {
  free(inst->elements);
  free(inst);
}

void print(dynamic_array *inst) {
  for (int i = 0; i < inst->size; i++) {
    printf("%d, ", inst->elements[i]);
  }
  printf("\n");
}

int main(int argc, char *argv[]) {
  int capacity = 8;
  dynamic_array *inst = create_dynamic_array(capacity);
  // print(inst);

  for (int i = 0; i < 10; i++) {
    append(inst, i);
  }

  insert(inst,2,10);
  update(inst,1,99);
  delete(inst,0);
  print(inst);



  gc(inst);

  return 0;
}
