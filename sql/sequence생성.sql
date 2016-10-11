--시퀀스 만들기(sequence)

create sequence author_seq
start with 1
increment by 1
maxvalue 9999999999; --int 10로 author_no를 설정해 주었으므로. 

drop sequence author_seq;

--sequence 테스트

select author_seq.nextval from dual; 

select * from author;