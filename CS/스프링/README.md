<details>
<summary> 템플릿 </summary>
<br>
<pre>


</pre>
</details>

<details>
<summary> Spring Data JPA에서 새로운 Entity인지 판단하는 방법은 무엇일까요? </summary>
<br>
<pre>
@id의 필드가 primitive 타입이면 null 체크 불가 → JPA가 insert/update 판단 못함
동작은 하지만 isNew()의 판단 가능성이 애매해진다. 실수로 id에 값을 넣었다면 insert해야 할것을 update하게 되는 문제 발생

여기서 새로운 Entity는 (아직 영속화되지 않은 Entity라는 뜻이다.)
1. @Id의 값으로 판단
가장 일반적인 방법으로 id에 해당하는 필드가 null인지 확인하며
isNew()로 확인한다.
- jpa는 id==null인 경우 이 엔티티를 새로운 객체로 간주하고, persist()를 호출
- id !=null이면 이미 DB에 존재하는 것으로 간주하고, merge()를 사용

2. Persistable<T> 인터페이스 사용
직접 구현해서 boolean isNew를 true로 하면 id가 null이 아니어도 insert를 수행

3. EntityManager의 contains() 메서드 사용

# save()할때 persist() , merge() 판단
새로운 Entity인지 여부는 JpaEntityInformation의 isNew(T entity)에 의해 판단됩니다. 다른 설정이 없으면 JpaEntityInformation의 
구현체 중 JpaMetamodelEntityInformation 클래스가 동작합니다. @Version이 사용된 필드가 없거나 @Version이 사용된 필드가 primitive 타입이면 
AbstractEntityInformation의 isNew()를 호출합니다. @Version이 사용된 필드가 wrapper class이면 null여부를 확인합니다.

@Version이 사용된 필드가 없어서 AbstractEntityInformation 클래스가 동작하면 @Id 어노테이션을 사용한 필드를 확인해서 primitive 타입이 
아니라면 null 여부, Number의 하위 타입이면 0인지 여부를 확인합니다.@GeneratedValue 어노테이션으로 키 생성 전략을 사용하면 데이터베이스에 
저장될 때 id가 할당됩니다. 따라서 데이터베이스에 저장되기 전에 메모리에서 생성된 객체는 id가 비어있기 때문에 isNew()는 true가 되어 새로운 
entity로 판단합니다.

# 직접 ID를 할당하는 경우에는 어떻게 동작하나요?
키 생성 전략을 사용하지 않고 직접 ID를 할당하는 경우 새로운 entity로 간주되지 않습니다. 이 때는 엔티티에서 Persistable<T> 인터페이스를 구현해서 
JpaMetamodelEntityInformation 클래스가 아닌 JpaPersistableEntityInformation의 isNew()가 동작하도록 해야 합니다.

# 새로운 Entity인지 판단하는게 왜 중요할까?
save 호출 시 isNew 로 merge, persist를 할지를 판단 하는데 id를 직접 지정하면 신규 entity로 보지 않아 merge를 수행한다.
이때 entity가 신규임에도 merge를 수행하여 select insert를 같이 수행하여 비효율이 발생
따라서 entity인지 판단하는것은 중요하다.

# merge는 왜 select 하고 insert할까?
안정성을 위해서이다. 혹시 update했는데 db에 값이 없으면 사고이다.
그래서 select로 값이 있으면 update하고 없으면 insert 하는 동작을 수행한다.
그리고 merge는 동작이 무겁고 예측이 어렵다.

</pre>

</details>


<details>
<summary> JPA의 ddl-auto 옵션은 각각 어떤 동작을 하고 어떤 상황에서 사용해야 할까요? </summary>
<br>
<pre>



</pre>
</details>

<details>
<summary> JPA의 N+1 문제에 대해서 설명해주세요 </summary>
<br>
<pre>

JPA N + 1 문제는 연관 관계가 설정된 엔티티를 조회할 경우에, 조회된 데이터 개수(N)만큼 연관관계의 조회 쿼리가 추가로 발생하는 현상입니다.
예를 들어, 블로그 게시글과 댓글이 있는 경우, 게시글을 조회한 후 각 게시글마다 댓글을 조회하기 위한 추가 쿼리가 발생할 수 있습니다. 
이를 N + 1 문제라고 합니다.

# findAll 메서드의 글로벌 패치 전략 별 N + 1 문제 상황에 대해서 설명해주세요
즉시 로딩으로 설정하여 내부의 쿼리가 한번더 발생하는 문제다. 지연 로딩으로 설정하면 문제를 해결할 수 있다.
이는 연관관계에 있는 엔티티를 프록시 객체로 생성하여 주입했기 때문에 가능합니다.


# N + 1 문제는 어떻게 해결할 수 있을까요?
fetch join, @EnittyGraph를 사용하는 방법이 있다.
fetch join은 연관 관계에 있는 엔티티를 한 번에 즉시 로딩하는 구문이다.


</pre>
</details>