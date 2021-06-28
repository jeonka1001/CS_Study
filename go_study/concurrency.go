package main

// import (
// 	"fmt"
// 	"sync"
// )

// var wg sync.WaitGroup

// func job(s string) {
// 	defer wg.Done()
// 	for i := 0; i < 3; i++ {
// 		// time.Sleep(time.Millisecond * 10)
// 		fmt.Println(s)
// 	}
// }

// func main() {
// 	fmt.Println("뭔 계란.. ? ")
// 	wg.Add(3)
// 	go job("육군에 출근")
// 	go job("해군에 출근")
// 	go job("공군에 출근")
// 	wg.Wait()
// 	// time.Sleep(time.Millisecond * 1000)
// }

// // 동시성 구현을 위해 GO 언어에서는 go 루틴을 사용한다.
// // go 루틴이 끝날 때 까지 기다리기 위해 앞에서는 time.Sleep 을 사용한다
// // 동기화 : time.Sleep() 는 좀 별로... ! 따라서 대기 그룹을 사용한다

// // 대기 그룹이란 ? time.Sleep 을 사용하지 않고 go 루틴을 기다려 주도록 하는 것
// // sync.WaitGroup 할당 후 Add Done Wait 을 사용함
// // Add : Go 루틴 추가
// // Done : GO 루틴 수행 종료 알림
// // Wait : 다른 GO 루틴 기다림
