package DB;

import AuxClass.Transport;
import AuxClass.User;

import java.util.ArrayList;

interface DBControl {
    public Transport logUser(Transport tran, Object List);
    public Transport creatBreack(Transport tran, Object List);
}
