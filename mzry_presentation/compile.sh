jobname=presentation
./clean.sh
rm $jobname.pdf
pdflatex -shell-escape ./$jobname.tex
pdflatex -shell-escape ./$jobname.tex
./clean.sh
clear
echo ****************************************************************************
echo 正式编译前应该访问https://github.com/shifujun/UESTCthesis检查模板是否有更新！
echo ****************************************************************************
