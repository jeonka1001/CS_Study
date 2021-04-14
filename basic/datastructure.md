# 면접준비 - 자료구조
> 나먼의 언어로 준비하는 면접준비

- [참고 사이트](#https://github.com/JaeYeopHan/Interview_Question_for_Beginner)

## 목차
1. [Array vs LinkedList](#array-vs-linkedlist)


### Array vs LinkedList

##### 데이터 참조
- Array 의 경우 배열 형태로 데이터를 관리하기 때문에 참조를 원하는 데이터에 직접 접근하여 빠른 시간에 참조할 수 있다. 따라서 O(1) 만큼의 시간이 걸린다
- LinkedList 의 경우 각 데이터가 이전 데이터와 다음 데이터에 대한 주소만 알고 있기 때문에 원하는 데이터 참조 시 모든 원소를 순회해야한다. 따라서 O(N) 만큼의 시간이 걸린다

##### 데이터 삽입 삭제
- Array 의 경우 배열 형태로 데이터를 관리하기 때문에 특정 데이터를 삽입 삭제 이후 배열의 빈 공간을 없애기 위해 임시 배열을 생성해서 데이터를 삽입/삭제 후 원본 배열을 복사한다.  따라서 최악의 경우 O(N)의 시간이 걸린다.
- LinkedList 의 경우 각 데이터가 이전,이후 데이터만 저장하고 있기 때문에 특정 데이터를 삽입/삭제 시 이후 데이터와 이전 데이터만 연결하면 된다. 따라서 O(1) 의 시간이 걸린다.


## 참고 사이트 
- [Map 관련](#https://rangken.github.io/blog/2015/java.map/)  
- [hash table](#https://mangkyu.tistory.com/102)  