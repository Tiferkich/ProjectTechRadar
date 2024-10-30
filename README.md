# ProjectTechRadar
T1 project for studing

Всем привет! Сложность работы для меня была в понятии, как получить jwt токен с приложения которое я не должен реализовывать.
В итоге я решил все таки сделать AuthController для авторизации...
Также вроде как сделал автоматическое добавление фильтров на эндпоинты для ролей юзеров.
То есть использовал вот эти таблички из задания, делал это через введение собственной анотации и рефлексии: 


![image](https://github.com/user-attachments/assets/eefce87a-1e2b-424c-be7f-c8fdbf4f8faf)




Swagger UI:
![image](https://github.com/user-attachments/assets/2c77ff8a-3de6-444d-afa4-0513b9824d4c)







Примеры работы эндпоинтов:

Получаем JWT
![image](https://github.com/user-attachments/assets/f9526cb1-66fe-4456-8b18-439fe7ba5d60)




Добавляем опрос:
![image](https://github.com/user-attachments/assets/0b74d757-9b70-461e-b7c0-6291b1d936d2)




От логина админа можем получить информацию для дешборда:
![image](https://github.com/user-attachments/assets/e0e2a56d-0c3a-4414-9f4c-55583e629392)


