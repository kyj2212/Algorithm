-- 코드를 입력하세요
SET @hour := -1; 

SELECT (@hour := @hour + 1) as HOUR, 
(SELECT COUNT(HOUR(DATETIME)) as COUNT from ANIMAL_OUTS where HOUR(DATETIME)=@hour) as COUNT
from ANIMAL_OUTS
WHERE @hour < 23
ORDER BY @hour ASC;