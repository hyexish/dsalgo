#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

// 链址法
typedef struct Node
{
    int key;
    int value;
    struct Node *next;
} Node;

typedef struct HashTable
{
    Node **table;
    int size;
    int capacity;
} HashTable;

HashTable *ht_create(int capacity)
{
    HashTable *ht = (HashTable *)malloc(sizeof(HashTable));
    ht->capacity = capacity;
    ht->size = 0;
    ht->table = (Node **)malloc(capacity * sizeof(Node *));
    for (int i = 0; i < capacity; i++)
    {
        ht->table[i] = NULL;
    }
    return ht;
}

void ht_insert(HashTable *ht, int key, int value)
{
    int index = key % ht->capacity;
    Node *node = (Node *)malloc(sizeof(Node));
    node->key = key;
    node->value = value;
    node->next = ht->table[index];
    ht->table[index] = node;
    ht->size++;
}

bool ht_contains(HashTable *ht, int key)
{
    int index = key % ht->capacity;
    Node *cur = ht->table[index];
    while (cur != NULL)
    {
        if (cur->key == key)
        {
            return true;
        }
        cur = cur->next;
    }
    return false;
}

int ht_search(HashTable *ht, int key)
{
    int index = key % ht->capacity;
    Node *cur = ht->table[index];
    while (cur != NULL)
    {
        if (cur->key == key)
        {
            return cur->value;
        }
        cur = cur->next;
    }
    return -1;
}

void ht_delete(HashTable *ht, int key)
{
    int index = key % ht->capacity;
    Node *cur = ht->table[index];
    Node *prev = NULL;
    while (cur != NULL)
    {
        if (cur->key == key)
        {
            if (prev == NULL)
                ht->table[index] = cur->next;
            else
                prev->next = cur->next;
            free(cur);
            ht->size--;
            return;
        }
        prev = cur;
        cur = cur->next;
    }
}

void ht_clear(HashTable *ht)
{
    for (int i = 0; i < ht->capacity; i++)
    {
        Node *cur = ht->table[i];
        while (cur != NULL)
        {
            Node *tmp = cur;
            cur = cur->next;
            free(tmp);
        }
        ht->table[i] = NULL;
    }
    ht->size = 0;
}

void ht_destory(HashTable *ht)
{
    ht_clear(ht);
    free(ht->table);
    free(ht);
}

void ht_print(HashTable *ht)
{
    for (int i = 0; i < ht->capacity; i++)
    {
        Node *cur = ht->table[i];
        printf("Index %d: ", i);
        while (cur != NULL)
        {
            printf("-> (%d, %d) ", cur->key, cur->value);
            cur = cur->next;
        }
        printf("\n");
    }
}
int main(int argc, char const *argv[])
{
    HashTable *ht = ht_create(10);
    ht_insert(ht, 1, 5);
    ht_insert(ht, 11, 5);
    ht_insert(ht, 2, 1);
    ht_insert(ht, 3, 1);
    ht_insert(ht, 4, 1);
    ht_insert(ht, 5, 1);
    ht_insert(ht, 15, 1);
    ht_insert(ht, 55, 1);
    ht_insert(ht, 25, 1);

    ht_print(ht);
    ht_destory(ht);
    return 0;
}
