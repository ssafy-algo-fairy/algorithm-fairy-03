# 니가 싫어 싫어 너무 싫어 싫어 오지 마 내게 찝쩍대지마 - 1 (20440) 풀이 발표

---

## 🌳 문제 핵심

> 모기가 방에 들어오고 나가는 시간 $T_E$, $T_X$의 범위가 최대 `2,100,000,000`입니다. 
> 시간마다 배열을 만들어 누적합을 구하면 메모리 초과(MLE)가 발생하므로, 
> **모기의 입장(+1)과 퇴장(-1) 시점을 '이벤트'로 묶어 시간순으로 훑고 지나가는 스위핑(Sweep Line) 알고리즘**을 사용해야 합니다.

---

## 💡 핵심 알고리즘 : 스위핑 (Sweep Line) / 정렬
핵심코드
```
    static void solve(){
        int maxCount = 0;
        int curCount = 0;
        boolean isMax = false;
        int maxStartTime = 0;
        int maxEndTime = 0;
        for(int i=0; i<2*N; i++){
            if(arr[i].isStart) curCount++;
            else curCount--;

            if(i+1 < 2*N && arr[i].time == arr[i+1].time) continue;
            
            if(curCount > maxCount){
                maxCount = curCount;
                isMax = true;
                maxStartTime = arr[i].time;
            }
            else if(curCount < maxCount && isMax){
                maxEndTime = arr[i].time;
                isMax = false;
            }
        }
        sb.append(maxCount).append("\n");
        sb.append(maxStartTime).append(" ").append(maxEndTime);
    }
```

text
누적합 배열의 한계 극복 = 21억 크기의 배열 대신, 2*N개의 이벤트 정렬로 해결
  └─ 모든 이벤트를 시간(time) 기준 오름차순으로 정렬
      └─ 순차적으로 이벤트를 탐색하며 curCount에 +1(입장) 또는 -1(퇴장) 반영
            ├─ 현재 이벤트와 다음 이벤트의 시간이 같다면 갱신 보류 (합산 우선 진행)
            ├─ 동시간대 처리가 끝난 후, curCount > maxCount 라면 최대치 갱신 (시작점 저장)
            └─ 최대치에서 내려오는 순간 (curCount < maxCount) 구간 닫기 (끝점 저장)

항목,내용

탐색 방식,스위핑 (Sweep Line)
시간 복잡도,O(N log N) (이벤트 배열 정렬)
이벤트 기준,"입장 시점(start)은 포함, 퇴장 시점(end)은 정각에 사라지므로 그대로 반영"
동시간대 처리,같은 시간에 발생하는 입/퇴장 이벤트를 일괄 합산 후 curCount 평가

🔑 Key Point:
동시간대 이벤트 일괄 처리가 가장 중요합니다. arr[i].time == arr[i+1].time일 때 continue를 통해 넘긴 로직이 핵심입니다. 예를 들어 오후 4시에 모기 1마리가 들어오고 동시에 1마리가 나갔을 때, 이를 한 번에 계산하지 않으면 일시적으로 모기 수가 증가한 것으로 판정되어 잘못된 최대 구간이 기록되거나 끊기게 됩니다.

🎯 결론 및 배울 점
시간 범위의 극복: 일반적인 누적합 배열(imos법)로 풀 수 없는 거대한 시간 범위를, 이벤트 발생 시점만 모아 정렬하는 스위핑으로 가볍게 해결하는 발상을 배웠습니다.

동시성 제어: 시간순으로 정렬된 데이터에서 "동일한 시간"에 벌어지는 변화를 쪼개지 않고 묶어서 평가(트랜잭션화)해야 하는 엣지 케이스 처리법을 익힐 수 있었습니다.
