/* 멀티미디어과학과 1110811 윤예나
   조약돌 놓기 문제 recursive */ 

#include "stdafx.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <windows.h>
#define max(a,b)(((a)>(b))?(a):(b))
#define Random(max) rand() % ((max))
#define MAXCOL 3 // 3*n table이므로 행은 3으로 고정

typedef enum Pattern{
	Pattern_one,
	Pattern_two,
	Pattern_three,
	Pattern_four
}Pattern;

int pebble(int i, int pattern, int** table);	// i열이 패턴 p로 놓일 때의 최고점수 구하기
int w(int row, int pattern, int** table);
bool Is_Compatible(int origin_pattern, int Comparsion_pattern);
int** Malloc(int col, int row);				// 2차원 배열 동적 할당
int** Free(int col, int row, int** arr);		// 2차원 배열 메모리 해제

int main()
{
	int** table; // 2차원 테이블
	int i, j, MAXROW, input_pattern;
	size_t result = 0; // 경로의 값 저장 
	int MAX = 0;

	printf("조약돌 Table의 열 크기 : ");
	scanf_s("%d", &MAXROW); // 행은 3으로 고정이므로 열만 입력받는다. 

	if(MAXROW<=0) {
		printf("잘못된 입력입니다.\n");
		exit(1);
	}

	table = Malloc(MAXCOL, MAXROW); // 2차원 배열 동적생성
	
	//Randomize(); 
	for(i=0; i<MAXCOL; i++) { // 2차원 배열 값 랜덤생성
		for(j=0; j<MAXROW; j++)
			table[i][j] = Random(50);
	}

	for(i=0; i<MAXCOL; i++) { // 2차원배열 출력
		for(j=0; j<MAXROW; j++) 
			printf(" %3d ", table[i][j]);
		
		fputc('\n', stdout);
	}
	
	LARGE_INTEGER liCounter1, liCounter2, liFrequency; 
    QueryPerformanceFrequency(&liFrequency); // 주파수(1초당 증가되는 카운트수)를 구한다. 
    QueryPerformanceCounter(&liCounter1); // 코드 수행 전 카운트 저장

	for(i=0; i <= Pattern_four; i++){ // i열이 패턴 p로 놓일 때의 최고점수 구하기
		MAX = pebble(MAXROW-1, i, table);
		result = ((int)result < MAX)? MAX : result;
	}
	
	QueryPerformanceCounter(&liCounter2); // 코드 수행 후 카운트 저장

	printf("result = %d\n", result);
	printf("recursive_수행 시간 = %f 초\n", (double)(liCounter2.QuadPart - liCounter1.QuadPart) / (double)liFrequency.QuadPart); 


	table = Free(MAXCOL, MAXROW, table); // 메모리 동적해제

	return 0;
}

// i열이 패턴 p로 놓일 때의 최고점수 구하기
// w[i,p] : i열이 패턴 p(pattern)로 놓일 때 i열에 돌이 놓인 곳의 점수합
int pebble(int i,  int pattern, int** table)
{
	int tmp = 0; // 이전 단계의 점수 합 임시 저장
	int max = 0; // 이번 단계의 점수 합
	int q; // 어떤 패턴인지 저장
	
	if(i==0) { // 1열인 경우, 한 줄의 최대값을 return
		return w(i, pattern, table);
	} else {
		max = -999; // 음수 무한대 - 매우 작은 값

		for(q=Pattern_one; q<=Pattern_four; q++) { // 4가지 패턴
			if (Is_Compatible(pattern,q)) { // 양립하면 수행
				tmp = pebble(i-1, q, table); // 패턴 하나 선택했을 때 점수 합 
				if(tmp > max)  // 양립하는 패턴 중 가장 큰 값 저장
					max = tmp;
			}
		}
	}
	return (max + w(i, pattern, table)); // 이전 단계까지의 최대값에 이번 열의 최대값 더한다.
}

// w[i,p] : i열이 패턴 p로 놓일 때 i열에 돌이 놓인 곳의 점수합.
int w(int i,  int pattern, int** table )
{
	int result=0;
	
	switch(pattern){
		case Pattern_one:
			result = table[Pattern_one][i];
			break;

		case Pattern_two:
			result = table[Pattern_two][i];
			break;

		case Pattern_three:
			result = table[Pattern_three][i];
			break;

		case Pattern_four:
			result += table[Pattern_one][i];
			result += table[Pattern_three][i];
			break;
	}
	return result;
}

bool Is_Compatible(int origin_pattern, int Comparsion_pattern)
{
	bool result = false; // 양립할 수 없음으로 초기화. true일 때, 양립할 수 있음 

	switch(origin_pattern){ // 원래 패턴과 양립할 수 있는지 확인
		case Pattern_one: // 패턴 1은 패턴 2,3과 양립할 수 있음
			if( Comparsion_pattern == Pattern_two || Comparsion_pattern == Pattern_three) 
				result = true;
			break;

		case Pattern_two: // 패턴 2는 패턴 1,3,4와 양립할 수 있음
			if( Comparsion_pattern == Pattern_one || Comparsion_pattern == Pattern_three || Comparsion_pattern == Pattern_four ) 
				result = true;
			break;

		case Pattern_three: // 패턴 3은 패턴 1,2와 양립할 수 있음
			if( Comparsion_pattern == Pattern_one || Comparsion_pattern == Pattern_two) 
				result = true;
			break;

		case Pattern_four: // 패턴 4는 패턴 2와 양립할 수 있음
			if( Comparsion_pattern == Pattern_two ) 
				result = true;
			break;
	}
	return result;
}

int** Malloc(int col, int row) // 동적 할당
{
	int **arr;
    int i;
 
    arr = (int**)malloc(col * sizeof(int*));
 
    for(i=0; i<col; i++)
		arr[i] = (int*)malloc(row * sizeof(int));
        
    return arr;
} 

int** Free(int col, int row, int** arr) // 동적 할당 해제
{
	int i;
 
    for(i=0; i<col; i++)
		free(arr[i]);
        
    free(arr);

    return arr;
}

int _tmain(int argc, _TCHAR* argv[])
{
	return 0;
}


