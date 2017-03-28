Реализовать приложение по работе со школами.
Минимально: бек (набор сервисов + бд)
Опционально: любая реализация фронтовой части под web
Реализовать проект необходимо используя принципы SOLID и DRY

Функциональность:
1. добавление/удаление/изменение школы (атрибуты: номер, адрес, ФИО директора, р/с)
2. добавление/удаление/изменение класса (атрибуты: номер, количество учеников, ФИО классного руководителя)
3. формирование xml/pdf (на твой выбор) файла со списком классов для выбранной школы

Требования:
1. java 8 и ее фичи
2. томкат 8х
3. бд - любая embedded (derby, h2 ...)
4. формат общения с фронтом - json
5. организовать логгирование операций
6. организовать обработку ошибок
7. применить Unit-тестирование (опционально - написать через TDD)

Работа с базой:
  
1.Получить список всех школ - метод GET
  пример curl -i -X GET http://localhost:8080/rest/schools/all
    
2.Получить данные одной школы по ее номеру(индексу)  - метод GET
  пример curl -i -X GET http://localhost:8080/rest2schools?id=12 
    
3.Удалить школу по номеру(индексу) - метод DELETE 
  пример curl -i -X DELETE http://localhost:8080/rest/schools/delete/12
    
4.Добавить школу - метод POST 
  пример curl -H "Content-Type: application/json" -i -X POST 
    -d '{"school_id":"222","address":"test","fioDirector":"new","accountNumber":"12345"}' 
      http://localhost:8080/rest/schools/post
    
5.Редактировать школу - метод PUT 
    пример curl -H "Content-Type: application/json" -i -X PUT
    -d '{"school_id":222,"address":"test","fioDirector":"new","accountNumber":"54321"}' 
      http://localhost:8080/rest/schools/put
    
6.Получить список всех классов выбранной школы по ее номеру(индексу) в XML - метод GET 
  пример curl -i -X GET http://localhost:8080/rest/schools/xml?id=12
  
7.Получить список всех классов - метод GET 
  пример curl -i -X GET http://localhost:8080/rest/class/all
    
8.Получить данные одного класса по индексу класса - метод GET
  пример curl -i -X GET http://localhost:8080/rest/class?id=1
    
9.Удалить класс по индексу - метод DELETE 
  curl -i -X DELETE http://localhost:8080/rest/class/delete/1
    
10.Добавить класс - метод POST 
  пример curl -H "Content-Type: application/json" -i -X POST 
    -d '{"className":"7A","numberPupil":33,"fioTeacher":"Мышкина М.","school":12}' 
    http://localhost:8080/rest/class/post
          
11.Редактировать класс - метод PUT /rest/class/put
  пример curl -H "Content-Type: application/json" -i -X PUT 
    -d '{"className":"1A","numberPupil":32,"fioTeacher":"Дудкина П.","school":12,"id":1}' 
    http://localhost:8080/rest/class/put
