#include <iostream>

class DynamicArray {
public:
  DynamicArray();
  DynamicArray(DynamicArray &&) = default;
  DynamicArray(const DynamicArray &) = default;
  DynamicArray &operator=(DynamicArray &&) = default;
  DynamicArray &operator=(const DynamicArray &) = default;
  ~DynamicArray();

private:
};

DynamicArray::DynamicArray() {}

DynamicArray::~DynamicArray() {}

int main(int argc, char *argv[]) { return 0; }
