/*********************** Closure ***************************/
class Human {
    String fName
    String lName
    def call(){
        "$fName $lName"
    }
}
def human = new Human(fName: 'Misha', lName: 'Petrov')
assert 'Misha Petrov' == human()

def myMethod() { return 1 + 1 }
human = this.&myMethod
assert 2 == human()

/**********************  RegExp  ***************************/
def result = ["foobar", "bazbar", "barquux"].grep(~/.*bar$/)
// тоже самое: def result = ["foobar", "bazbar", "barquux"].findAll {it ==~ /.*bar$/}
assert ["foobar", "bazbar"] == result

def matches = "12345624" =~ /2./
assert matches.getCount() == 2 // 23 24

def dashedToCamelCase(String orig) {
    orig.replaceAll (/-(\w)/)  { fullMatch, firstCharacter -> firstCharacter.toUpperCase() }
}
assert "firstName" == dashedToCamelCase("first-name")
assert "oneTwoThreeFourFiveSixSevenEight" == dashedToCamelCase("one-two-three-four-five-six-seven-eight")

/**********************  Mock  ****************************/
class TranslationService {
    String convert(String key) {
        return "test"
    }
}
//Создание mock - просто взяли map, объявили Closure и сказали - это TranslationService
def service = [convert: { String key -> 'some text' }] as TranslationService
assert 'some text' == service.convert('key.text')

/********************* Assert *****************************/
assert 13 == "13" as Integer
//не просто кинет Exception, ещё и сделает пояснения
def x = [1, 2, 3, 4, 5]
assert (x << 6) == [1, 2, 3, 4, 5, 6]