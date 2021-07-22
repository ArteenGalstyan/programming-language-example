# programming-language-example


## Language ##

### Abstract Grammar ###

```
i is an int
op is an operator
e is an expression

op ::= '+' | '-' | '*'
e ::= i | e1 op e2
```

### Concrete Grammar ###

```
i is an int
e is an expression
a is an additive expression
m is a multiplicative expression
p is a primary expression

e ::= a
a ::= m (('+'|'-') m)*
m ::= p ('*' p)*
p ::= i | '(' e ')'
```

### Tokens ###

- Integer tokens
- Plus token
- Minus token
- Multiplication token