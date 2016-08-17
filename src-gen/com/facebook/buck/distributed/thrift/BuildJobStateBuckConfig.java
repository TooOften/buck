/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.facebook.buck.distributed.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-07-26")
public class BuildJobStateBuckConfig implements org.apache.thrift.TBase<BuildJobStateBuckConfig, BuildJobStateBuckConfig._Fields>, java.io.Serializable, Cloneable, Comparable<BuildJobStateBuckConfig> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("BuildJobStateBuckConfig");

  private static final org.apache.thrift.protocol.TField USER_ENVIRONMENT_FIELD_DESC = new org.apache.thrift.protocol.TField("userEnvironment", org.apache.thrift.protocol.TType.MAP, (short)1);
  private static final org.apache.thrift.protocol.TField RAW_BUCK_CONFIG_FIELD_DESC = new org.apache.thrift.protocol.TField("rawBuckConfig", org.apache.thrift.protocol.TType.MAP, (short)2);
  private static final org.apache.thrift.protocol.TField ARCHITECTURE_FIELD_DESC = new org.apache.thrift.protocol.TField("architecture", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField PLATFORM_FIELD_DESC = new org.apache.thrift.protocol.TField("platform", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new BuildJobStateBuckConfigStandardSchemeFactory());
    schemes.put(TupleScheme.class, new BuildJobStateBuckConfigTupleSchemeFactory());
  }

  public Map<String,String> userEnvironment; // optional
  public Map<String,List<OrderedStringMapEntry>> rawBuckConfig; // optional
  public String architecture; // optional
  public String platform; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    USER_ENVIRONMENT((short)1, "userEnvironment"),
    RAW_BUCK_CONFIG((short)2, "rawBuckConfig"),
    ARCHITECTURE((short)3, "architecture"),
    PLATFORM((short)4, "platform");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // USER_ENVIRONMENT
          return USER_ENVIRONMENT;
        case 2: // RAW_BUCK_CONFIG
          return RAW_BUCK_CONFIG;
        case 3: // ARCHITECTURE
          return ARCHITECTURE;
        case 4: // PLATFORM
          return PLATFORM;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.USER_ENVIRONMENT,_Fields.RAW_BUCK_CONFIG,_Fields.ARCHITECTURE,_Fields.PLATFORM};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.USER_ENVIRONMENT, new org.apache.thrift.meta_data.FieldMetaData("userEnvironment", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.RAW_BUCK_CONFIG, new org.apache.thrift.meta_data.FieldMetaData("rawBuckConfig", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
                new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, OrderedStringMapEntry.class)))));
    tmpMap.put(_Fields.ARCHITECTURE, new org.apache.thrift.meta_data.FieldMetaData("architecture", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PLATFORM, new org.apache.thrift.meta_data.FieldMetaData("platform", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(BuildJobStateBuckConfig.class, metaDataMap);
  }

  public BuildJobStateBuckConfig() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public BuildJobStateBuckConfig(BuildJobStateBuckConfig other) {
    if (other.isSetUserEnvironment()) {
      Map<String,String> __this__userEnvironment = new HashMap<String,String>(other.userEnvironment);
      this.userEnvironment = __this__userEnvironment;
    }
    if (other.isSetRawBuckConfig()) {
      Map<String,List<OrderedStringMapEntry>> __this__rawBuckConfig = new HashMap<String,List<OrderedStringMapEntry>>(other.rawBuckConfig.size());
      for (Map.Entry<String, List<OrderedStringMapEntry>> other_element : other.rawBuckConfig.entrySet()) {

        String other_element_key = other_element.getKey();
        List<OrderedStringMapEntry> other_element_value = other_element.getValue();

        String __this__rawBuckConfig_copy_key = other_element_key;

        List<OrderedStringMapEntry> __this__rawBuckConfig_copy_value = new ArrayList<OrderedStringMapEntry>(other_element_value.size());
        for (OrderedStringMapEntry other_element_value_element : other_element_value) {
          __this__rawBuckConfig_copy_value.add(new OrderedStringMapEntry(other_element_value_element));
        }

        __this__rawBuckConfig.put(__this__rawBuckConfig_copy_key, __this__rawBuckConfig_copy_value);
      }
      this.rawBuckConfig = __this__rawBuckConfig;
    }
    if (other.isSetArchitecture()) {
      this.architecture = other.architecture;
    }
    if (other.isSetPlatform()) {
      this.platform = other.platform;
    }
  }

  public BuildJobStateBuckConfig deepCopy() {
    return new BuildJobStateBuckConfig(this);
  }

  @Override
  public void clear() {
    this.userEnvironment = null;
    this.rawBuckConfig = null;
    this.architecture = null;
    this.platform = null;
  }

  public int getUserEnvironmentSize() {
    return (this.userEnvironment == null) ? 0 : this.userEnvironment.size();
  }

  public void putToUserEnvironment(String key, String val) {
    if (this.userEnvironment == null) {
      this.userEnvironment = new HashMap<String,String>();
    }
    this.userEnvironment.put(key, val);
  }

  public Map<String,String> getUserEnvironment() {
    return this.userEnvironment;
  }

  public BuildJobStateBuckConfig setUserEnvironment(Map<String,String> userEnvironment) {
    this.userEnvironment = userEnvironment;
    return this;
  }

  public void unsetUserEnvironment() {
    this.userEnvironment = null;
  }

  /** Returns true if field userEnvironment is set (has been assigned a value) and false otherwise */
  public boolean isSetUserEnvironment() {
    return this.userEnvironment != null;
  }

  public void setUserEnvironmentIsSet(boolean value) {
    if (!value) {
      this.userEnvironment = null;
    }
  }

  public int getRawBuckConfigSize() {
    return (this.rawBuckConfig == null) ? 0 : this.rawBuckConfig.size();
  }

  public void putToRawBuckConfig(String key, List<OrderedStringMapEntry> val) {
    if (this.rawBuckConfig == null) {
      this.rawBuckConfig = new HashMap<String,List<OrderedStringMapEntry>>();
    }
    this.rawBuckConfig.put(key, val);
  }

  public Map<String,List<OrderedStringMapEntry>> getRawBuckConfig() {
    return this.rawBuckConfig;
  }

  public BuildJobStateBuckConfig setRawBuckConfig(Map<String,List<OrderedStringMapEntry>> rawBuckConfig) {
    this.rawBuckConfig = rawBuckConfig;
    return this;
  }

  public void unsetRawBuckConfig() {
    this.rawBuckConfig = null;
  }

  /** Returns true if field rawBuckConfig is set (has been assigned a value) and false otherwise */
  public boolean isSetRawBuckConfig() {
    return this.rawBuckConfig != null;
  }

  public void setRawBuckConfigIsSet(boolean value) {
    if (!value) {
      this.rawBuckConfig = null;
    }
  }

  public String getArchitecture() {
    return this.architecture;
  }

  public BuildJobStateBuckConfig setArchitecture(String architecture) {
    this.architecture = architecture;
    return this;
  }

  public void unsetArchitecture() {
    this.architecture = null;
  }

  /** Returns true if field architecture is set (has been assigned a value) and false otherwise */
  public boolean isSetArchitecture() {
    return this.architecture != null;
  }

  public void setArchitectureIsSet(boolean value) {
    if (!value) {
      this.architecture = null;
    }
  }

  public String getPlatform() {
    return this.platform;
  }

  public BuildJobStateBuckConfig setPlatform(String platform) {
    this.platform = platform;
    return this;
  }

  public void unsetPlatform() {
    this.platform = null;
  }

  /** Returns true if field platform is set (has been assigned a value) and false otherwise */
  public boolean isSetPlatform() {
    return this.platform != null;
  }

  public void setPlatformIsSet(boolean value) {
    if (!value) {
      this.platform = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case USER_ENVIRONMENT:
      if (value == null) {
        unsetUserEnvironment();
      } else {
        setUserEnvironment((Map<String,String>)value);
      }
      break;

    case RAW_BUCK_CONFIG:
      if (value == null) {
        unsetRawBuckConfig();
      } else {
        setRawBuckConfig((Map<String,List<OrderedStringMapEntry>>)value);
      }
      break;

    case ARCHITECTURE:
      if (value == null) {
        unsetArchitecture();
      } else {
        setArchitecture((String)value);
      }
      break;

    case PLATFORM:
      if (value == null) {
        unsetPlatform();
      } else {
        setPlatform((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case USER_ENVIRONMENT:
      return getUserEnvironment();

    case RAW_BUCK_CONFIG:
      return getRawBuckConfig();

    case ARCHITECTURE:
      return getArchitecture();

    case PLATFORM:
      return getPlatform();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case USER_ENVIRONMENT:
      return isSetUserEnvironment();
    case RAW_BUCK_CONFIG:
      return isSetRawBuckConfig();
    case ARCHITECTURE:
      return isSetArchitecture();
    case PLATFORM:
      return isSetPlatform();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof BuildJobStateBuckConfig)
      return this.equals((BuildJobStateBuckConfig)that);
    return false;
  }

  public boolean equals(BuildJobStateBuckConfig that) {
    if (that == null)
      return false;

    boolean this_present_userEnvironment = true && this.isSetUserEnvironment();
    boolean that_present_userEnvironment = true && that.isSetUserEnvironment();
    if (this_present_userEnvironment || that_present_userEnvironment) {
      if (!(this_present_userEnvironment && that_present_userEnvironment))
        return false;
      if (!this.userEnvironment.equals(that.userEnvironment))
        return false;
    }

    boolean this_present_rawBuckConfig = true && this.isSetRawBuckConfig();
    boolean that_present_rawBuckConfig = true && that.isSetRawBuckConfig();
    if (this_present_rawBuckConfig || that_present_rawBuckConfig) {
      if (!(this_present_rawBuckConfig && that_present_rawBuckConfig))
        return false;
      if (!this.rawBuckConfig.equals(that.rawBuckConfig))
        return false;
    }

    boolean this_present_architecture = true && this.isSetArchitecture();
    boolean that_present_architecture = true && that.isSetArchitecture();
    if (this_present_architecture || that_present_architecture) {
      if (!(this_present_architecture && that_present_architecture))
        return false;
      if (!this.architecture.equals(that.architecture))
        return false;
    }

    boolean this_present_platform = true && this.isSetPlatform();
    boolean that_present_platform = true && that.isSetPlatform();
    if (this_present_platform || that_present_platform) {
      if (!(this_present_platform && that_present_platform))
        return false;
      if (!this.platform.equals(that.platform))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_userEnvironment = true && (isSetUserEnvironment());
    list.add(present_userEnvironment);
    if (present_userEnvironment)
      list.add(userEnvironment);

    boolean present_rawBuckConfig = true && (isSetRawBuckConfig());
    list.add(present_rawBuckConfig);
    if (present_rawBuckConfig)
      list.add(rawBuckConfig);

    boolean present_architecture = true && (isSetArchitecture());
    list.add(present_architecture);
    if (present_architecture)
      list.add(architecture);

    boolean present_platform = true && (isSetPlatform());
    list.add(present_platform);
    if (present_platform)
      list.add(platform);

    return list.hashCode();
  }

  @Override
  public int compareTo(BuildJobStateBuckConfig other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetUserEnvironment()).compareTo(other.isSetUserEnvironment());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUserEnvironment()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.userEnvironment, other.userEnvironment);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRawBuckConfig()).compareTo(other.isSetRawBuckConfig());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRawBuckConfig()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.rawBuckConfig, other.rawBuckConfig);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetArchitecture()).compareTo(other.isSetArchitecture());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetArchitecture()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.architecture, other.architecture);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPlatform()).compareTo(other.isSetPlatform());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPlatform()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.platform, other.platform);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("BuildJobStateBuckConfig(");
    boolean first = true;

    if (isSetUserEnvironment()) {
      sb.append("userEnvironment:");
      if (this.userEnvironment == null) {
        sb.append("null");
      } else {
        sb.append(this.userEnvironment);
      }
      first = false;
    }
    if (isSetRawBuckConfig()) {
      if (!first) sb.append(", ");
      sb.append("rawBuckConfig:");
      if (this.rawBuckConfig == null) {
        sb.append("null");
      } else {
        sb.append(this.rawBuckConfig);
      }
      first = false;
    }
    if (isSetArchitecture()) {
      if (!first) sb.append(", ");
      sb.append("architecture:");
      if (this.architecture == null) {
        sb.append("null");
      } else {
        sb.append(this.architecture);
      }
      first = false;
    }
    if (isSetPlatform()) {
      if (!first) sb.append(", ");
      sb.append("platform:");
      if (this.platform == null) {
        sb.append("null");
      } else {
        sb.append(this.platform);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class BuildJobStateBuckConfigStandardSchemeFactory implements SchemeFactory {
    public BuildJobStateBuckConfigStandardScheme getScheme() {
      return new BuildJobStateBuckConfigStandardScheme();
    }
  }

  private static class BuildJobStateBuckConfigStandardScheme extends StandardScheme<BuildJobStateBuckConfig> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, BuildJobStateBuckConfig struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // USER_ENVIRONMENT
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map16 = iprot.readMapBegin();
                struct.userEnvironment = new HashMap<String,String>(2*_map16.size);
                String _key17;
                String _val18;
                for (int _i19 = 0; _i19 < _map16.size; ++_i19)
                {
                  _key17 = iprot.readString();
                  _val18 = iprot.readString();
                  struct.userEnvironment.put(_key17, _val18);
                }
                iprot.readMapEnd();
              }
              struct.setUserEnvironmentIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // RAW_BUCK_CONFIG
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map20 = iprot.readMapBegin();
                struct.rawBuckConfig = new HashMap<String,List<OrderedStringMapEntry>>(2*_map20.size);
                String _key21;
                List<OrderedStringMapEntry> _val22;
                for (int _i23 = 0; _i23 < _map20.size; ++_i23)
                {
                  _key21 = iprot.readString();
                  {
                    org.apache.thrift.protocol.TList _list24 = iprot.readListBegin();
                    _val22 = new ArrayList<OrderedStringMapEntry>(_list24.size);
                    OrderedStringMapEntry _elem25;
                    for (int _i26 = 0; _i26 < _list24.size; ++_i26)
                    {
                      _elem25 = new OrderedStringMapEntry();
                      _elem25.read(iprot);
                      _val22.add(_elem25);
                    }
                    iprot.readListEnd();
                  }
                  struct.rawBuckConfig.put(_key21, _val22);
                }
                iprot.readMapEnd();
              }
              struct.setRawBuckConfigIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // ARCHITECTURE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.architecture = iprot.readString();
              struct.setArchitectureIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // PLATFORM
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.platform = iprot.readString();
              struct.setPlatformIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, BuildJobStateBuckConfig struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.userEnvironment != null) {
        if (struct.isSetUserEnvironment()) {
          oprot.writeFieldBegin(USER_ENVIRONMENT_FIELD_DESC);
          {
            oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, struct.userEnvironment.size()));
            for (Map.Entry<String, String> _iter27 : struct.userEnvironment.entrySet())
            {
              oprot.writeString(_iter27.getKey());
              oprot.writeString(_iter27.getValue());
            }
            oprot.writeMapEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.rawBuckConfig != null) {
        if (struct.isSetRawBuckConfig()) {
          oprot.writeFieldBegin(RAW_BUCK_CONFIG_FIELD_DESC);
          {
            oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.LIST, struct.rawBuckConfig.size()));
            for (Map.Entry<String, List<OrderedStringMapEntry>> _iter28 : struct.rawBuckConfig.entrySet())
            {
              oprot.writeString(_iter28.getKey());
              {
                oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, _iter28.getValue().size()));
                for (OrderedStringMapEntry _iter29 : _iter28.getValue())
                {
                  _iter29.write(oprot);
                }
                oprot.writeListEnd();
              }
            }
            oprot.writeMapEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.architecture != null) {
        if (struct.isSetArchitecture()) {
          oprot.writeFieldBegin(ARCHITECTURE_FIELD_DESC);
          oprot.writeString(struct.architecture);
          oprot.writeFieldEnd();
        }
      }
      if (struct.platform != null) {
        if (struct.isSetPlatform()) {
          oprot.writeFieldBegin(PLATFORM_FIELD_DESC);
          oprot.writeString(struct.platform);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class BuildJobStateBuckConfigTupleSchemeFactory implements SchemeFactory {
    public BuildJobStateBuckConfigTupleScheme getScheme() {
      return new BuildJobStateBuckConfigTupleScheme();
    }
  }

  private static class BuildJobStateBuckConfigTupleScheme extends TupleScheme<BuildJobStateBuckConfig> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, BuildJobStateBuckConfig struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetUserEnvironment()) {
        optionals.set(0);
      }
      if (struct.isSetRawBuckConfig()) {
        optionals.set(1);
      }
      if (struct.isSetArchitecture()) {
        optionals.set(2);
      }
      if (struct.isSetPlatform()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetUserEnvironment()) {
        {
          oprot.writeI32(struct.userEnvironment.size());
          for (Map.Entry<String, String> _iter30 : struct.userEnvironment.entrySet())
          {
            oprot.writeString(_iter30.getKey());
            oprot.writeString(_iter30.getValue());
          }
        }
      }
      if (struct.isSetRawBuckConfig()) {
        {
          oprot.writeI32(struct.rawBuckConfig.size());
          for (Map.Entry<String, List<OrderedStringMapEntry>> _iter31 : struct.rawBuckConfig.entrySet())
          {
            oprot.writeString(_iter31.getKey());
            {
              oprot.writeI32(_iter31.getValue().size());
              for (OrderedStringMapEntry _iter32 : _iter31.getValue())
              {
                _iter32.write(oprot);
              }
            }
          }
        }
      }
      if (struct.isSetArchitecture()) {
        oprot.writeString(struct.architecture);
      }
      if (struct.isSetPlatform()) {
        oprot.writeString(struct.platform);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, BuildJobStateBuckConfig struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TMap _map33 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.userEnvironment = new HashMap<String,String>(2*_map33.size);
          String _key34;
          String _val35;
          for (int _i36 = 0; _i36 < _map33.size; ++_i36)
          {
            _key34 = iprot.readString();
            _val35 = iprot.readString();
            struct.userEnvironment.put(_key34, _val35);
          }
        }
        struct.setUserEnvironmentIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TMap _map37 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.LIST, iprot.readI32());
          struct.rawBuckConfig = new HashMap<String,List<OrderedStringMapEntry>>(2*_map37.size);
          String _key38;
          List<OrderedStringMapEntry> _val39;
          for (int _i40 = 0; _i40 < _map37.size; ++_i40)
          {
            _key38 = iprot.readString();
            {
              org.apache.thrift.protocol.TList _list41 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
              _val39 = new ArrayList<OrderedStringMapEntry>(_list41.size);
              OrderedStringMapEntry _elem42;
              for (int _i43 = 0; _i43 < _list41.size; ++_i43)
              {
                _elem42 = new OrderedStringMapEntry();
                _elem42.read(iprot);
                _val39.add(_elem42);
              }
            }
            struct.rawBuckConfig.put(_key38, _val39);
          }
        }
        struct.setRawBuckConfigIsSet(true);
      }
      if (incoming.get(2)) {
        struct.architecture = iprot.readString();
        struct.setArchitectureIsSet(true);
      }
      if (incoming.get(3)) {
        struct.platform = iprot.readString();
        struct.setPlatformIsSet(true);
      }
    }
  }

}

