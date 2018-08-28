pragma solidity ^0.4.24;
contract Reputation{
    address owner;
    constructor () public {
        owner = msg.sender;
    }

    struct userMessage {
        string id_card;
        string passworld;
    }


    mapping(address => uint)  mapp_score;
    mapping(address => userMessage) mapp_verifty;

    modifier mod_owner {
        require(owner == msg.sender, "你不是创始人");

        _;
    }


    function ownerSetUser(address _user, string _id_card, string _passworld) public mod_owner returns(bool) {
        userMessage storage um = mapp_verifty[_user];
        um.id_card = _id_card;
        um.passworld = _passworld;
        mapp_verifty[_user] = um;
        mapp_score[_user] = 500;
        return true;
    }

    function ownerSetScore (address _user, uint _score) public mod_owner returns(bool) {
        mapp_score[_user] = _score;
        return true;
    }

    function ownerGetScore (address _user) public view mod_owner  returns (uint) {
        return mapp_score[_user];
    }

    function userGetScore (address _user, string _id_card, string _password) public view returns(uint) {
        require(mapp_score[_user] != 0);
        require(keccak256(mapp_verifty[_user].id_card) == keccak256(_id_card) && keccak256(mapp_verifty[_user].passworld) == keccak256(_password)  );
        return mapp_score[_user];
    }
}