import time
import psutil
import pymysql

while True:
    for i in [1, 2, 3]:

        mydb = pymysql.connect(
            host='localhost',
            port=3306,
            user='root',
            password='sptech',
            database='test'
        )

        cpuPercent = psutil.cpu_percent()
        ramPercent = psutil.virtual_memory().percent

        if mydb.open:
            print("|               Banco conectado               |\n----------------------------------------- ")
            print("-------------------------------------------")
            print("| Porcentagem de uso de CPU: {:.2f}%".format(cpuPercent), " |\n------------------------------------------- ")
            print("---------------------------------------------")
            print("| Porcentagem de uso de RAM: {:.2f}%".format(ramPercent), " |\n--------------------------------------------- ")
            print("-------------------------------------------------------------")

            dadosInsertCpu = [cpuPercent]
            dadosInsertRam = [ramPercent]

            mycursor = mydb.cursor()

            sql_query_cpu = "INSERT INTO RegistrosTRUSTED (dadosCapturados, dataHora, fkComponente, fkIdservidor) VALUES (%s, now(), 1, 1)"
            mycursor.execute(sql_query_cpu, dadosInsertCpu)

            sql_query_ram = "INSERT INTO RegistrosTRUSTED (dadosCapturados, dataHora, fkComponente, fkIdservidor) VALUES (%s, now(), 2, 1)"
            mycursor.execute(sql_query_ram, dadosInsertRam)

            mydb.commit()

            mycursor.close()

        mydb.close()
        time.sleep(5)
