from flask import request, jsonify, Flask
import pymysql.cursors

connection = pymysql.connect(host='ghostsec.mysql.pythonanywhere-services.com', user='ghostsec', password='$Yung1998',
                             database='ghostsec$default')

app = Flask(__name__)


@app.route('/register', methods=['POST'])
def register():
    try:
        json = request.json
        user = json['user']
        email = json['email']
        phone = json['phone']
        password = json['password']
        confirm = json['confirm']

        if "@" not in email:
            response = jsonify({'msg': "Invalid email"})
            response.status_code = 10
            return response
        elif " " not in user:
            response = jsonify({'msg': "Invalid username"})
            response.status_code = 20
            return response
        elif len(password) < 8:
            response = jsonify({'msg': "Have atleast 8 characters password"})
            response.status_code = 30
            return response
        elif password != confirm:
            response = jsonify({'msg': "Passwords dont match!"})
            response.status_code = 40
            return response



        else:

            cursor = connection.cursor(pymysql.cursors.DictCursor)

            sql = 'insert into users (user_name, user_email, user_phone, user_password)' \
                  'values(%s, %s, %s, %s)'
            cursor.execute(sql, (user, email, phone, password))
            connection.commit()
            response = jsonify({'msg': 'Saved Successfully'})
            response.status_code = 200
            return response

    except:
        connection.rollback()
        response = jsonify({'msg': "Internal server error"})
        response.status_code = 500
        return response


@app.route('/login', methods=['POST'])
def login():
    try:
        json = request.json
        email = json['email']
        password = json['password']

        cursor = connection.cursor(pymysql.cursors.DictCursor)
        sql = 'select * from users where user_email=%s and user_password=%s'

        cursor.execute(sql, (email, password))

        if cursor.rowcount == 0:
            response = jsonify({'msg': "Invalid Login Credentials"})
            response.status_code = 401
            return response
        elif cursor.rowcount == 1:
            response = jsonify({'msg': "Login Successful"})
            response.status_code = 200
            return response
        else:
            response = jsonify({'msg': "Bad Request"})
            response.status_code = 400
            return response
    except:
        response = jsonify({'msg': "Internal Server Error"})
        response.status_code = 500
        return response


app.run(debug=True)
# end

# no render template
# data is in form of JSON format ( Javascript Object Notation)
# standard way to transfer and receive files on different applications
# end points ->  routes
# Post -> send info
#  get -> receive info
# put -> update info
# delete -> delete info
