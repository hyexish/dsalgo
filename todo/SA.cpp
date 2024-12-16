#include <algorithm>
#include <cstdio>
#include <cstring>
#include <iostream>

using namespace std;

const int N = 1000010;

/**
 * Suffix Array 后缀数组
 * @param sa[] 后缀数组
 * @param rk[] 排名数组
 */
int n, w, sa[N], rk[N << 1], tp[N << 1], oldrk[N << 1];

/**
 * 
 * 构建后缀数组
 * @param s 主串
 */

/**
 * 倍增算法+快速排序
 * Binary Lifting
 * T(n)=O(nlog^2n)
 */
void build_1(char *s);
/**
 * 倍增算法+基数排序
 * T(n)=O(nlogn)
 */
void build_2(char *s);
/**
 * 诱导排序
 * SA-IS
 * T(n)=O(n)
 */
void build_3(char *s);
/**
 * 
 * DC3算法
 * T(n)=O(n)
 */
void build_4(char *s);

void print();

void height();

void build_1(char *s){
	int i, p;
	n = strlen(s) - 1;
	for(i = 1; i <= n; ++i)
	{
		sa[i] = i;
		rk[i] = s[i];
	}
	for(w = 1; w <= n; w <<= 1)
	{
		sort(sa + 1, sa + n + 1,[](int x,int y){
			return rk[x] == rk[y] ? rk[x + w] < rk[y + w] : rk[x] < rk[y];
		});
		memcpy(tp,rk,sizeof(rk));
		for(p = 1, i = 1; i <= n; ++i)
		{
			if(tp[sa[i]] == tp[sa[i - 1]] 
				&& tp[sa[i] + w] == tp[sa[i - 1] + w])
				rk[sa[i]] = p;
			else rk[sa[i]] = ++p;
		}
	}
}

/**
 * 打印后缀数组
 * 
 */
void print(int *sa)
{
	for(int i = 1; i <= n; ++i) printf("%d, ", sa[i]);
}

void height(){
	
}
/**
 * 主函数
 * 
 */
int main(int argc, char const *argv[])
{
	/* code */
	char s[] = "$aabaaaab";
	build_1(s);
	print(sa);
	return 0;
}