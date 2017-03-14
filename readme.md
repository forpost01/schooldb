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