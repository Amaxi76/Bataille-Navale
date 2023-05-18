cd ../sae_2.03_grp_08/src/bataillenavale

echo $(pwd)

javac @compile.list -encoding utf-8

cd ..

echo "nom du serveur : " $(hostname)

java bataillenavale.Serveur
