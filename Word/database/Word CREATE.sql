CREATE TABLE Users (
    user_id INT NOT NULL CONSTRAINT pk_users_id PRIMARY KEY,
    name VARCHAR2(30),
    second_name VARCHAR2(30),
    birth_date DATE,
    email VARCHAR2(30) CONSTRAINT unique_email_user UNIQUE,
    password VARCHAR2(32)
);

CREATE TABLE TaskType (
    task_type_id INT NOT NULL CONSTRAINT pk_task_type PRIMARY KEY,
    name VARCHAR2(30)
);

CREATE TABLE ExamCategories (
    category_id INT NOT NULL CONSTRAINT pk_category_id PRIMARY KEY,
    name VARCHAR2(45)
);

CREATE TABLE Cars (
    car_id INT NOT NULL CONSTRAINT pk_car_id PRIMARY KEY,
    category_id INT NOT NULL CONSTRAINT fk_car_category_id REFERENCES ExamCategories(category_id)
);

CREATE TABLE Positions (
    position_id INT NOT NULL CONSTRAINT pk_positions_id PRIMARY KEY,
    role VARCHAR2(30) CONSTRAINT position_role_unique UNIQUE,
    description VARCHAR2(1000)
);
 
CREATE TABLE UserRoles (
    user_id INT NOT NULL CONSTRAINT fk_userroles_users_id REFERENCES Users(user_id),
    position_id INT NOT NULL CONSTRAINT fk_userroles_positions_id REFERENCES Positions(position_id),
    CONSTRAINT user_roles_pk PRIMARY KEY (user_id, position_id)
);
 
CREATE TABLE Operations (
    operation_id INT NOT NULL CONSTRAINT pk_operations_id PRIMARY KEY,
    name VARCHAR2(30),
    type INT
);
 
CREATE TABLE Resources (
    resource_id INT NOT NULL CONSTRAINT pk_resources_id PRIMARY KEY,
    name VARCHAR2(30)
);
 
CREATE TABLE Permissions (
    permission_id INT NOT NULL CONSTRAINT pk_permissions PRIMARY KEY,
    position_id INT NOT NULL CONSTRAINT fk_permissions_positions_id REFERENCES Positions(position_id),
    resource_id INT NOT NULL CONSTRAINT fk_permissions_resources_id REFERENCES Resources(resource_id),
    operation_id INT NOT NULL CONSTRAINT fk_permissions_operations_id REFERENCES Operations(operation_id)
);

CREATE TABLE Day (
    day_number INT NOT NULL CONSTRAINT pk_day_id PRIMARY KEY,
    CONSTRAINT day_range CHECK (day_number BETWEEN 1 AND 31)
);

CREATE TABLE Month (
    month_number INT NOT NULL CONSTRAINT pk_month PRIMARY KEY,
    CONSTRAINT month_range CHECK (month_number BETWEEN 1 AND 12)
);

CREATE TABLE Year (
    year_number INT NOT NULL CONSTRAINT pk_year PRIMARY KEY,
    CONSTRAINT year_range CHECK (year_number BETWEEN 2019 AND 2100)
);

CREATE TABLE Hour (
    hour_id INT NOT NULL CONSTRAINT pk_hour PRIMARY KEY,
    hour INT NOT NULL,
    minute INT NOT NULL,
    CONSTRAINT hour_range CHECK (hour BETWEEN 0 AND 23),
    CONSTRAINT minute_range CHECK (minute BETWEEN 0 AND 59)
);

CREATE TABLE Term (
    term_id INT NOT NULL CONSTRAINT pk_term PRIMARY KEY,
    year_number INT CONSTRAINT term_year REFERENCES Year(year_number),
    month_number INT CONSTRAINT term_month REFERENCES Month(month_number),
    day_number INT CONSTRAINT term_day REFERENCES Day(day_number),
    hour_id INT CONSTRAINT term_hour REFERENCES Hour(hour_id)
);

CREATE TABLE ExaminationCards (
    examcard_id INT NOT NULL CONSTRAINT pk_examcard_id PRIMARY KEY,
    comments VARCHAR2(1000),
    result VARCHAR(15) NULL,
    car_id INT NOT NULL CONSTRAINT fk_exam_car_id REFERENCES Cars(car_id),
    category_id INT NOT NULL CONSTRAINT fk_exam_category_id REFERENCES ExamCategories(category_id),
    term_id INT NOT NULL CONSTRAINT fk_examcard_termin_id REFERENCES Term(term_id),
    resources_id INT NOT NULL CONSTRAINT fk_examcard_resource_id REFERENCES Resources(resource_id),
    examiner INT CONSTRAINT fk_examcard_examiner REFERENCES Users(user_id),
    examined INT NOT NULL CONSTRAINT fk_examcard_examined REFERENCES Users(user_id),
    CONSTRAINT examiner_examined_same CHECK (examiner != examined)
);

CREATE TABLE ExamTasks (
    task_id INT NOT NULL CONSTRAINT pk_task_id PRIMARY KEY,
    name VARCHAR2(100),
    possible_mistakes INT,
    task_type_id INT NOT NULL CONSTRAINT fk_task_tasktype REFERENCES TaskType(task_type_id)
);

CREATE TABLE CategoryTask (
    category_id INT NOT NULL CONSTRAINT fk_category_categorytask REFERENCES ExamCategories(category_id),
    task_id INT NOT NULL CONSTRAINT fk_task_categorytask REFERENCES ExamTasks(task_id),
    CONSTRAINT pk_categorytask PRIMARY KEY (category_id, task_id)
);

CREATE TABLE ExamTaskResult (
    examTaskResult_id INT NOT NULL CONSTRAINT pk_examTaskResult PRIMARY KEY,
    result INT,
    task_id INT NOT NULL CONSTRAINT fk_task_taskresult REFERENCES ExamTasks(task_id),
    examcard_id INT NOT NULL CONSTRAINT fk_examcard_taskresult REFERENCES ExaminationCards(examcard_id)
);

CREATE TABLE Questions (
    question_id INT NOT NULL CONSTRAINT pk_question_id PRIMARY KEY,
    question VARCHAR2(1000),
    image BLOB,
    extension VARCHAR2(10),
    answer1 VARCHAR(100),
    answer2 VARCHAR(100),
    answer3 VARCHAR(100),   
    correct_answer VARCHAR(100),
    resource_id INT NOT NULL CONSTRAINT fk_question_resources_id REFERENCES Resources(resource_id)
);

CREATE TABLE CategoryQuestion (
    question_id INT NOT NULL CONSTRAINT fk_question_categoryquestion REFERENCES Questions(question_id),
    category_id INT NOT NULL CONSTRAINT fk_category_categoryquestion REFERENCES ExamCategories(category_id),
    CONSTRAINT pk_categoryquestion PRIMARY KEY (question_id, category_id)
);    
    
CREATE TABLE TheoreticalExams (
    exam_id INT NOT NULL CONSTRAINT pk_theoreticalexam_id PRIMARY KEY,
    is_open NUMBER(1) DEFAULT 0,  
    category_id INT NOT NULL CONSTRAINT fk_teoexam_category_id REFERENCES ExamCategories(category_id),
    term_id INT NOT NULL CONSTRAINT fk_theoriticalExams_termin REFERENCES Term(term_id),
    resources_id INT NOT NULL CONSTRAINT fk_texam_resource_id REFERENCES Resources(resource_id),
    user_id_examiner INT CONSTRAINT fk_texam_user_id REFERENCES Users(user_id)
); 
 
CREATE TABLE TheoreticalExamUser (
    user_id_examined INT NOT NULL CONSTRAINT fk_theoreticalExamUser_user REFERENCES Users(user_id),
    theoreticalExam INT NOT NULL CONSTRAINT fk_theoreticalExamUser_exam REFERENCES TheoreticalExams(exam_id)
);
 
CREATE TABLE ExamResults (
    examresult_id INT NOT NULL CONSTRAINT pk_examresult PRIMARY KEY,
    wynik VARCHAR2(15),
    theoreticalexam_id INT NOT NULL CONSTRAINT fk_examresult_texam_id REFERENCES TheoreticalExams(exam_id),
    resources_id INT NOT NULL CONSTRAINT fk_examresult_resource_id REFERENCES Resources(resource_id),
    user_id_egzaminowany INT NOT NULL CONSTRAINT fk_examresult_user_id REFERENCES Users(user_id)
);

CREATE TABLE QuestionResult (
    question_result_id INT NOT NULL CONSTRAINT pk_question_result PRIMARY KEY,
    question_id INT NOT NULL CONSTRAINT fk_question_questionresult REFERENCES Questions(question_id),
    examresult_id INT NOT NULL CONSTRAINT fk_examresult_questionresult REFERENCES ExamResults(examresult_id),
    user_answer VARCHAR2(100),
    result INT
);

CREATE SEQUENCE Users_Seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE Positions_Seq
START WITH 1
INCREMENT BY 1;  

CREATE SEQUENCE Operations_Seq
START WITH 1
INCREMENT BY 1;  

CREATE SEQUENCE Resources_Seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE examination_cards_seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE exam_categories_seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE exam_tasks_seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE questions_seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE hour_seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE term_seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE permission_seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE theoritical_exam_seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE exam_task_result_seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE examResult_seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE question_result_seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE task_type_seq
START WITH 1
INCREMENT BY 1;