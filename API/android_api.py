import flask, json, os
from flask import Flask, request

app = Flask('__main__', template_folder="", static_folder="", root_path="", static_url_path="")
msgs = []

@app.route('/')
def index_page():
    return ("Hello ovde Pavle")

@app.route('/json/<number>')
def prikaz_jednog(number=None):
    try:
        with open("realestate_detail.json") as f:
            data = json.load(f)
            for el in data:
                if el['id'] == number:
                    return str(el)
    except(Exception):
        return "Greška"

@app.route('/json')
def ret_json():
    return flask.send_file("predsednici.json")

@app.route('/glas/<number>', methods=["POST"])
def dodaj_glas(number=None):
    
    try:
        if os.path.isfile("predsednici.json"):
            print("psotoji")
        data=[]
        with open("predsednici.json", encoding="utf-8") as f:
            data = json.load(f)
            
            for el in data:
                
                print(el['id'])
                if el['id'] == number:
                    
                    el['glasova'] +=1
        with open("predsednici.json", 'w',  encoding="utf-8") as f:
            json.dump(data, f, indent=4)
        return "dada"
    except Exception as e:
        print(str(e))

@app.route('/add', methods=['POST'])
def add_new():
    body = flask.request.form
    estate = json.loads(body['data'])
    print(estate)
    data = []
    try:
        with open("realestate.json", 'r', encoding='utf-8') as f:
            data = json.load(f)
            data.append(estate)
            with open("realestate.json", 'w', encoding='utf-8') as of:
                json.dump(data, of, ensure_ascii=False)
                return "OK"

        
    except(Exception):
        print(Exception)
        return "Greška"

            


app.run("0.0.0.0", 5000)
