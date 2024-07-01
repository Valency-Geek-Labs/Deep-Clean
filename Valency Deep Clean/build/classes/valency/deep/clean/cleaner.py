import socket
import pandas as pd
from sklearn.impute import SimpleImputer
import os

filepath = None
num_box = None
cat_box = None
technique = None
operation = None
isnume = None
iscat = None

def start_server():
    #For connection
    host = "localhost"
    port = 4040
    
    #Starting Server with other credentials
    with socket.socket(socket.AF_INET,socket.SOCK_STREAM) as serversocket:
        serversocket.bind((host,port))
        serversocket.listen(1)
        print(f'Server listening on {host}:{port}')
       
        while True:                                 #Continously listenning on host
            conn,addr = serversocket.accept()
            
            with conn:
                print(f'Connected by address {addr}')
                #Receive Handshake and sent ack packet
                whole_data = conn.recv(1024).decode('utf-8')
                received_str = whole_data.strip()
                if(received_str == 'CLEAN'):
                    send_loc = clean_csv()
                    conn.sendall(send_loc.encode('utf-8'))
                else:
                    print(f'The received data is {whole_data}')
                    response = clean_data(whole_data)
                    conn.sendall(response.encode('utf-8'))
               
def clean_data(data):
    
    #Get all data
    global filepath,num_box,cat_box,technique,operation
    filepath,num_box,cat_box,technique,operation = data.split(',')
    global isnume,iscat
    isnume = False
    iscat = False
    
    #Processing data
    filepath_han = filepath.strip()
    
    #Check the boxes
    if(num_box == 'checked'):
        isnume = True
    
    if(cat_box == 'checked'):
        iscat = True
  
    csvs = pd.read_csv(filepath_han)
    is_dirt = csvs.isnull().sum()
    isdirt = is_dirt.to_string()
    print(isdirt)
    print(isnume,iscat)
    
    #Check whether the csv contains null value
    if(csvs.isnull().values.any()):
        isempty = True
    else:
        isempty = False

    total_value = f"{isdirt} \nContains missing values: {isempty}"
    

    return total_value

def clean_csv():
    global filepath,num_box,cat_box,technique,operation
    correct_path = filepath.strip()

    path_to_csv = pd.read_csv(correct_path)

    #Identifying and seperating missing columns
    missing_columns = path_to_csv.columns[path_to_csv.isnull().any()]
    numeric_values = []
    categorical_values = []

    for column in missing_columns:
        if pd.api.types.is_numeric_dtype(path_to_csv[column]):
            numeric_values.append(column)
        else:
            categorical_values.append(column)
    
    if technique == 'Removing':
        path_to_csv.dropna(axis=0,inplace=True)

        save_location = os.path.expanduser("~/Documents")
        file_name = 'Valency'
        folder_path = os.path.join(save_location,file_name)
        
        if not os.path.exists(folder_path):
            os.makedirs(folder_path)
        
        final_path = os.path.join(folder_path,"Cleaned File.csv")

        path_to_csv.to_csv(final_path,index=False)
        
        return "File save to the location: " + final_path
    
    elif technique == "Filling":
        #This is for fill and strategy
        if isnume & iscat:
            #Initialize the imputer
            exact_operation = operation.strip()
            lower_exact_operation = exact_operation.lower()
            imputer_num = SimpleImputer(strategy=lower_exact_operation)

            path_to_csv[numeric_values] = imputer_num.fit_transform(path_to_csv[numeric_values])
            
            #This is for Categorical values
            imputer_cat = SimpleImputer(strategy='most_frequent')
            path_to_csv[categorical_values] = imputer_cat.fit_transform(path_to_csv[categorical_values])

            #Save the file
            save_location = os.path.expanduser("~/Documents")
            file_name = 'Valency'
            folder_path = os.path.join(save_location,file_name)
        
            if not os.path.exists(folder_path):
               os.makedirs(folder_path)
        
            final_path = os.path.join(folder_path,"Cleaned File.csv")

            path_to_csv.to_csv(final_path,index=False)
        
            return "File save to the location: " + final_path

        elif isnume:
             exact_operation = operation.strip()
             lower_exact_operation = exact_operation.lower()
             imputer_num = SimpleImputer(strategy=lower_exact_operation)
             path_to_csv[numeric_values] = imputer_num.fit_transform(path_to_csv[numeric_values])
             
             save_location = os.path.expanduser("~/Documents")
             file_name = 'Valency'
             folder_path = os.path.join(save_location,file_name)
        
             if not os.path.exists(folder_path):
               os.makedirs(folder_path)
        
             final_path = os.path.join(folder_path,"Cleaned File.csv")

             path_to_csv.to_csv(final_path,index=False)
        
             return "File save to the location: " + final_path

        elif iscat:
             imputer_cat = SimpleImputer(strategy='most_frequent')
             path_to_csv[categorical_values] = imputer_cat.fit_transform(path_to_csv[categorical_values])
             print("cat value cleaning done")

             save_location = os.path.expanduser("~/Documents")
             file_name = 'Valency'
             folder_path = os.path.join(save_location,file_name)
        
             if not os.path.exists(folder_path):
               os.makedirs(folder_path)
        
             final_path = os.path.join(folder_path,"Cleaned File.csv")

             path_to_csv.to_csv(final_path,index=False)
        
             return "File save to the location: " + final_path

if __name__ == "__main__":
    start_server()            
