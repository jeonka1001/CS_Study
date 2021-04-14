# 면접 준비 - JAVA
> 나만의 언어로 하는 면접준비

## 질문 목차



### Java의 대표 Collection에는 무엇이 있을까?
- 대표적으로 List Map Set Stack Queue 가 있습니다.  
- 이 Collection 은 추상화된 Interface 이며, 특정 기능에 맞게 Interface를 구현한 구현체 또한 존재합니다.  
- List 를 예로 들자면 List를 구현한 ArrayList 와 LinkedList 가 존재합니다.

##### List란 ? 
- 배열과 비슷한 자료구조이며, 배열보다 편리한 기능을 많이 갖고있다.
- 대표적으로는 ArrayList 와 LinkedList가 있다.  

||Array|List|
|---|---|---|
|저장|메모리 공간에 연속적|메모리 공간에 불연속적(포인터개념)|
|크기|고정 크기( 변경 시 재 선언 후 원본 복제 )| 동적 크기 ( 유동적으로 변함 )|
|검색|연속적이기에 빠르다|불연속적이기에 느리다|
|삽입 및 삭제| 빈 공간을 다시 채워야 하기 때문에 느리다|해당 데이터를 삭제후 이전,이후 데이터와 연결만 해주면 되기 때문에 빠르다|


##### Map 이란?
- Key와 Value 를 한 쌍으로 저장하는 자료구조 입니다.
- 대표적으로는 HashMap LinkedHashMap TreeMap 이 있습니다.
- TreeMap 의 경우 
