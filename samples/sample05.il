.assembly extern mscorlib {}
.assembly sample05{}
.module sample05.exe
.class public _Principal{
.method static public void _principal()
{ .entrypoint
.locals(int64 i)
.locals(float64 nota1)
.locals(float64 nota2)
.locals(float64 soma)
ldc.i8 0
stloc i
ldc.r8 0.0
stloc soma
label1:
ldloc i
ldc.i8 1
add
stloc i
call void _Principal::_cargaHorária()
ldstr "digite uma nota (enter) após outra: "
call void [mscorlib]System.Console::Write(string)
ldstr "\n"
call void [mscorlib]System.Console::Write(string)
call string [mscorlib]System.Console::ReadLine()
call float64 [mscorlib]System.Double::Parse(string)
stloc nota1
call string [mscorlib]System.Console::ReadLine()
call float64 [mscorlib]System.Double::Parse(string)
stloc nota2
ldloc soma
ldloc nota1
ldloc nota2
call float64 _Principal::_média(float64,float64)
add
stloc soma
ldloc i
ldc.i8 3
ceq
brfalse label1
ldloc soma
ldc.i8 3
div
call void [mscorlib]System.Console::Write(float64)
ret
}
.method public static float64 _média(float64 n1,float64 n2) {
ldarg n1
ldarg n2
add
ldc.i8 2
div
ret
}
.method public static void _cargaHorária() {
.locals(int64 CH)
.locals(string disciplina)
ldstr "qual a disciplina? "
call void [mscorlib]System.Console::Write(string)
call string [mscorlib]System.Console::ReadLine()
stloc disciplina
label2:
ldstr "qual a CH da disciplina? "
call void [mscorlib]System.Console::Write(string)
call string [mscorlib]System.Console::ReadLine()
call int64 [mscorlib]System.Int64::Parse(string)
stloc CH
ldloc CH
ldc.i8 17
cgt
brfalse label2
ldstr "total de créditos: "
call void [mscorlib]System.Console::Write(string)
ldloc CH
ldc.i8 18
div
call void [mscorlib]System.Console::Write(int64)
ldstr "\n"
call void [mscorlib]System.Console::Write(string)
ret
}
}
