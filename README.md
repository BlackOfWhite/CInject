# CInject
Self made and lightweight dependency injection library. 

# Supports:

1. Constructor injection

```
public class Company {

    private Employee employee;

    public Company() {

    }

    @CInject
    public Company(Employee employee) {
        this.employee = employee;
    }
}
```

2. Field injection
```
public class Company {

    @CInject
    private Employee employee;
}
```

3. Injection with value
```
public class Engineer implements Employee {
    
    @CInject("Fantasy")
    private Book book;
}
```

4. Default implementation
```
@Default
public class Engineer implements Employee {
}
```
