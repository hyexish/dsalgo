#include <stdlib.h>
#include <stdio.h>
#include <limits.h>

typedef struct heap{
	int *elements;
	int capacity;
	int size;
} heap;

int left_child(int index);
int right_child(int index);
int  max(int *arr,int a, int b);
void swim(heap *instance);
void swim(heap *instance);
void heapify(heap *instance);
void swap(int *arr, int i, int j);
int compare(int a, int b);

int left_child(int index){
	return index * 2 + 1;
}

int right_child(int index){
	return index * 2 + 2;
}

void swim(heap *instance){

}

void sink(heap *instance){
	
}

void swap(int *arr, int i, int j){
	int tmp = arr[i];
	arr[i] = arr[j];
	arr[j] = tmp;
}

int compare(int a, int b){
  return a - b;	
}

int max(int *arr, int i, int j){
	return arr[i] > arr[j] ? i : j;
}

void *init_heap(){
    heap *instance = (heap*)malloc(sizeof(heap));
    instance->capacity = 10;
    instance->size = 0;
    instance->elements = (int *)malloc(sizeof(int)*10);
    return instance;
}
void append(heap *instance,int value){
  instance->elements[instance->size++]  = value;
}

void print_metadata(heap *instance){
  printf("%s=%d\n","capacity",instance->capacity);
  printf("%s=%d\n","size",instance->size );
  printf("%s= ","elements");

}

void heapify(heap *instance){
	for (int i = instance->size / 2 - 1; i >= 0; --i)
	{
       int left = left_child(i);
       int right = right_child(i);
       int maxIndex = max(instance->elements,left,right);
       if (compare(instance->elements[i],instance->elements[maxIndex] > 0))
       {
       	swap(instance->elements,i,maxIndex);
       }
	}
}


void print_elements(heap *instance){
	for (int i = 0; i < instance->size; ++i)
	{
       printf("%d, ",instance->elements[i]);
	}
	printf("\n");
}

int main(int argc, char const *argv[])
{
	heap *instance = init_heap();
	int arr[] = {2, 4, 1, 5, 7, 3, 6};
	int arr_szie = sizeof(arr) / sizeof(arr[0]);
	for(int i=1; i <= arr_szie; i++){
		append(instance,arr[i-1]);
	}
	heapify (instance);

  print_elements(instance);
	return 0;
}
