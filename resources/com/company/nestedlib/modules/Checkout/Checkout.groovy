echo "Hello I am using MPL nested library in Checkout stage"
echo "Checkout code base from repo "
echo CFG.'git.url'
echo CFG.'git.branch'
sh git clone CFG.'git.url'
sh git checkout CFG.'git.branch'